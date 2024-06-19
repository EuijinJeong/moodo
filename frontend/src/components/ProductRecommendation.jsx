import React from 'react';
import ProductItem from './ProductItem';
import '../css/product_recommendation.css'; // Include CSS for styling

const products = [
    { id: 1, name: '치이카와 인형 25cm', price: '9,000원', time: '21시간 전', image: 'path/to/image1.jpg' },
    { id: 2, name: '스웨이드 미들부츠', price: '20,000원', time: '2일 전', image: 'path/to/image2.jpg' },
    // 데이터베이스에서 저장된 아이템 불러와서 여기 변수에다가 저장해서 화면에 띄워야 함.
];

const ProductRecommendation = () => {
    return (
        <section className="product-recommendation">
            <h2>오늘의 상품 추천</h2>
            <div className="product-list">
                {products.map(product => (
                    <ProductItem key={product.id} product={product} />
                ))}
            </div>
        </section>
    );
}

export default ProductRecommendation;
