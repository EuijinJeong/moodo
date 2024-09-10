import React, {useState} from 'react';
import {useLocation, useNavigate} from "react-router-dom";
import ProductDetail from "./ProductDetail";
import ShippingForm from "./ShippingForm";
import axios from "axios";

const ProductCheckOut = () => {

    const location = useLocation();
    const navigate = useNavigate();
    const product = location.state?.product;

    const [address, setAddress] = useState({
        recipient: '',
        phone: '',
        addressLine1: '',
        addressLine2: '',
        postalCode: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setAddress({
            ...address,
            [name]: value
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Shipping Address:', address);
    };

    /**
     * 아임포트를 활용한 결제 처리 함수
     *
     * @returns {Promise<void>}
     */
    const handlePayment = async () => {
        // 유효성 검사
        if (!address.recipient || !address.phone || !address.addressLine1 || !address.postalCode) {
            alert('배송지 정보를 모두 입력해 주세요.');
            return;
        }

        console.log('일반 결제 시작');

        // 아임포트 결제 요청
        const { IMP } = window; // 아임포트 SDK 초기화
        IMP.init('imp81518761'); // 아임포트 관리자 페이지에서 발급된 가맹점 식별코드

        IMP.request_pay({
            pg: 'html5_inicis', // 사용할 PG사 (예: 'html5_inicis', 'kcp', 'kakao', 'paypal')
            pay_method: 'card', // 결제 수단 (예: 'card', 'trans', 'vbank', 'phone')
            merchant_uid: `mid_${new Date().getTime()}`, // 고유 주문번호
            name: product.title, // 결제할 상품 이름
            amount: product.price, // 결제 금액
            buyer_email: 'buyer@example.com', // 구매자 이메일
            buyer_name: address.recipient, // 구매자 이름
            buyer_tel: address.phone, // 구매자 전화번호
            buyer_addr: `${address.addressLine1} ${address.addressLine2}`, // 구매자 주소
            buyer_postcode: address.postalCode, // 구매자 우편번호
        }, async (response) => {
            if (response.success) {
                // 결제 성공 시 처리
                try {
                    const token = localStorage.getItem('token');
                    await axios.post('/api/payments', {
                        imp_uid: response.imp_uid, // 아임포트 고유 결제번호
                        merchant_uid: response.merchant_uid, // 상점에서 생성한 고유 주문번호
                        productId: product.id,
                        price: product.price,
                        address,
                    }, {
                        headers: {
                            Authorization: `Bearer ${token}`
                        }
                    });

                    // 결제 성공 시 결제 성공 페이지로 이동
                    navigate('/payment-success');
                } catch (error) {
                    console.error('결제 처리 중 오류가 발생했습니다.', error);
                    alert('결제 처리 중 오류가 발생했습니다.');
                }
            } else {
                // 결제 실패 시 처리
                alert(`결제 실패: ${response.error_msg}`);
            }
        });
    };

    if (!product) {
        return <div>상품 정보가 없습니다.</div>;
    }

    return (
        <div className="checkout-page">
            <ProductDetail product={product} />
            <form onSubmit={handleSubmit}>
                <ShippingForm address={address} handleChange={handleChange} />
                <button type="button" onClick={handlePayment} className="payment-button">
                    결제하기
                </button>
            </form>
        </div>
    );
};

export default ProductCheckOut;