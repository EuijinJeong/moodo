import React, {useState} from 'react';
import "../css/ProductRegistration.css"

const ProductRegistration = () => {
    const [images, setImages] = useState([]);
    const [title, setTitle] = useState('');
    const [condition, setCondition] = useState('');
    const [description, setDescription] = useState('');
    const [price, setPrice] = useState('');
    const [acceptOffers, setAcceptOffers] = useState(false);

    const handleImageChange = (e) => {
        if (e.target.files.length > 0) {
            const newImages = Array.from(e.target.files).map(file => ({
                file,
                url: URL.createObjectURL(file)
            }));
            setImages([...images, ...newImages]);
        }
    };

    const handleTitleChange = (e) => {
        setTitle(e.target.value);
    };

    const handleConditionChange = (e) => {
        setCondition(e.target.value);
    };

    const handleDescriptionChange = (e) => {
        setDescription(e.target.value);
    };

    const handlePriceChange = (e) => {
        setPrice(e.target.value);
    };

    const handleAcceptOffersChange = (e) => {
        setAcceptOffers(e.target.checked);
    };
    const handleSubmit = (e) => {
      e.preventDefault();
      // 등록하기 로직 아래에 작성
        console.log({
            title,
            condition,
            description,
            price,
            acceptOffers,
            images
        });
    };

    return (
        <div className="product-registration">
            <h1>상품 정보</h1>
            <div className="separator"></div>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>상품 이미지</label>
                    <input type="file" multiple onChange={handleImageChange} />
                </div>
                <div className="form-group">
                    <label>상품명</label>
                    <input type="text" value={title} placeholder="상품명을 입력해 주세요." onChange={handleTitleChange}/>
                </div>
                <div className="form-group">
                    <label>상품상태</label>
                    <div className="radio-group">
                        <label>
                            <input
                            type="radio"
                            value="new"
                            checked={condition === 'new'}
                            onChange={handleConditionChange}/>
                            <span>미개봉 상품</span>
                        </label>
                        <label>
                            <input
                                type="radio"
                                value="used"
                                checked={condition === 'used'}
                                onChange={handleConditionChange}/>
                            <span>중고 상품</span>
                        </label>
                    </div>
                </div>
                <div className="form-group">
                    <label>상품설명</label>
                    <textarea value={description} placeholder="상품 설명을 최대한 자세하게 적어주세요." onChange={handleDescriptionChange}></textarea>
                </div>
                <div className="form-group">
                    <label>가격</label>
                    <input type="number" value={price} placeholder="가격을 입력해 주세요." onChange={handlePriceChange} />
                </div>
                <div className="checkbox-container">
                    <label className="checkbox-label">
                        <input type="checkbox"
                               checked={acceptOffers}
                               onChange={handleAcceptOffersChange} />
                        <span>가격 제안 받기</span>
                    </label>
                </div>
                <div className="form-actions">
                    <button type="button" onClick={() => console.log('임시저장')}>임시저장</button>
                    <button type="submit">등록하기</button>
                </div>
            </form>
        </div>
    );
};

export default ProductRegistration;