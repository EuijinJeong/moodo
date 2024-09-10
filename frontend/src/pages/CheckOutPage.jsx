import React from 'react';
import UserDashboardHeader from "../components/UserDashboardHeader";
import ShippingForm from "../components/ShippingForm";
import ProductCheckOut from "../components/ProductCheckOut";

const CheckOutPage = () => {
    return (
        <div>
            <UserDashboardHeader />
            <ShippingForm />
            <ProductCheckOut />
        </div>
    );
};

export default CheckOutPage;