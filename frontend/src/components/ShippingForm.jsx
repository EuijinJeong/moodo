import React, {useState} from 'react';
import axios from "axios";

const ShippingForm = ({memberId}) => {
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

    const handleSubmit = async (e) => {
        e.preventDefault();

        // 유효성 검사 (예: 필수 입력 필드가 비어 있는지 확인)
        if (!address.recipient || !address.phone || !address.addressLine1 || !address.postalCode) {
            alert('모든 필수 필드를 입력해 주세요.');
            return;
        }

        try {
            // 서버로 데이터 전송
            const token = localStorage.getItem('token'); // Authorization 토큰을 로컬 스토리지에서 가져옴
            const response = await axios.post(`/api/members/${memberId}/addresses`, address, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
            });
            console.log('Shipping data submitted:', response.data);
            alert('배송지 정보가 성공적으로 제출되었습니다.');
        } catch (error) {
            console.error('Error submitting shipping data:', error);
            alert('배송지 정보 제출 중 오류가 발생했습니다.');
        }

        // 여기서 address 데이터를 처리하거나 제출할 수 있음
        console.log('Shipping Address:', address);
    };

    return (
        <form className="shipping-info" onSubmit={handleSubmit}>
            <h2>배송지 정보</h2>

            <label>수령인</label>
            <input
                type="text"
                name="recipient"
                value={address.recipient}
                onChange={handleChange}
                placeholder="수령인 이름"
            />

            <label>전화번호</label>
            <input
                type="text"
                name="phone"
                value={address.phone}
                onChange={handleChange}
                placeholder="전화번호"
            />

            <label>주소 1</label>
            <input
                type="text"
                name="addressLine1"
                value={address.addressLine1}
                onChange={handleChange}
                placeholder="주소"
            />

            <label>주소 2</label>
            <input
                type="text"
                name="addressLine2"
                value={address.addressLine2}
                onChange={handleChange}
                placeholder="상세 주소"
            />

            <label>우편번호</label>
            <input
                type="text"
                name="postalCode"
                value={address.postalCode}
                onChange={handleChange}
                placeholder="우편번호"
            />

            <button type="submit">배송지 저장</button>
        </form>
    );
};

export default ShippingForm;