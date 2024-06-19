import React, {useContext, useEffect, useState} from 'react';
import axios from "axios";
import "../css/product_detail.css";

const ProductDetail = ({ productId }) => {
    const [product, setProduct] = useState(null);
    const [storeInfo, setStoreInfo] = useState(null);


    useEffect(() => {
        const fetchProductAndStoreInfo = async () => {
            try {
                const token = localStorage.getItem('token');

                // 제품 정보를 가져오는 API 호출
                const productResponse = await axios.get(`/api/products-detail/${productId}`, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });

                const productData = productResponse.data;
                console.log(productData); // 제품 정보 잘 뜸
                setProduct(productData);

                // 제품 정보에서 store_id 추출
                const storeId = productData.store?.id;
                console.log("Store ID:", storeId); // store_id 콘솔 출력

                // 상점 정보를 가져오는 API 호출
                if (storeId) {
                    const storeResponse = await axios.get(`/api/stores/${storeId}`, {
                        headers: {
                            Authorization: `Bearer ${token}`
                        }
                    });

                    setStoreInfo(storeResponse.data);
                    console.log(storeResponse.data); 
                } else {
                    console.error('Store ID is not defined in the product data');
                }
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };
        fetchProductAndStoreInfo();
    }, [productId]);

    if (!product || !storeInfo) {
        return <div>Loading...</div>;
    }

    const handleStoreMove = () => {
        const storeId = storeInfo.id; // 상점 ID를 가져옵니다
        window.location.href = `/store/${storeId}`; // 상점 페이지로 이동합니다
    };

    const handleLike = async () => {
        try {
            const token = localStorage.getItem('token');
            const response = await axios.post(`/api/products/${productId}/like`, {}, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });

            // 여기 아래에 찜을 누르는 제품이 사용자의 제품인지 유효성 검사 로직 추가 해야함
            // 만약 본인제품이 아니라면 ok 아니면 오류 메세지 출력

            setProduct(response.data);
        } catch (e) {
            console.error("Error liking product:", e);
        }
    };

    return (
        <div>
            <div className="product-detail-inbox">
                <div className="product-detail__image">
                    <img src={`http://localhost:8080/images/${product.fileUrls[0]}`} alt={product.title} />
                </div>
                <div className="product-detail__info">
                    <h1>{product.title}</h1>
                    <p className="product-detail__price">{product.price.toLocaleString()} 원</p>
                    <div className="product-detail__status">
                        <span className="product-detail__label">상품상태: </span>
                        <span className="product-detail__value">{product.condition}</span>
                    </div>
                    <div className="product-detail__actions">
                        <button className="product-detail__like" onClick={handleLike}>
                            찜 {product.likedCount}
                        </button>
                        <button className="product-detail__chat">모두톡</button>
                        <button className="product-detail__buy">바로구매</button>
                    </div>
                </div>
            </div>
            <div className="product-detail__bottom">
                <div className="product-detail__additional-info">
                    <h2>상품정보</h2>
                    <p className="product-detail__description"> {product.description}</p>
                </div>
                <div className="product-detail__divider"></div>
                <div className="store-info">
                    <h2>상점정보</h2>
                    <p><strong>상점명:</strong> {storeInfo.name}</p>
                    <p><strong>상품 수:</strong> {storeInfo.productsCount}</p>
                    <p>{storeInfo.description}</p>
                    <button className="store-move-button" onClick={handleStoreMove}>상점으로 이동</button>
                </div>
            </div>
        </div>
    );
};

export default ProductDetail;
