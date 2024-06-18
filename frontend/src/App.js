import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import MainPage from './pages/MainPage';
import SignUpPage from "./pages/SignUpPage";
import FindPassword from "./pages/FindPassword";
import BookTradingMainPage from "./pages/BookTradingMainPage";
import ProductRegistrationPage from "./pages/ProductRegistrationPage";
import MyStorePage from "./pages/MyStorePage";
import ProductDetailPage from "./pages/ProductDetailPage";
import UserStorePage from "./pages/UserStorePage";

function App() {
  return (
    <Router>
        <Routes>
            <Route path="/" element={<MainPage />} /> 
            <Route path="/SignUpPage" element={<SignUpPage />} />
            <Route path="/FindPassword" element={<FindPassword />} />
            <Route path="/BookTradingMainPage" element={<BookTradingMainPage />} />
            <Route path="/ProductRegistrationPage" element={<ProductRegistrationPage />} />
            <Route path="/MyStorePage" element={<MyStorePage />} />
            <Route path="/product/:productId" element={<ProductDetailPage />} />
            <Route path="/store/:storeId" element={<UserStorePage />} />
        </Routes>
    </Router>
  );
}

export default App;
