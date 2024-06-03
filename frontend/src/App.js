import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import MainPage from './pages/MainPage';
import SignUpPage from "./pages/SignUpPage";
import FindPassword from "./pages/FindPassword";
import BookTradingMainPage from "./pages/BookTradingMainPage";
import ProductRegistration from "./components/ProductRegistration";
import ProductRegistrationPage from "./pages/ProductRegistrationPage";

function App() {
  return (
    <Router>
        <Routes>
            <Route path="/" element={<MainPage />} /> 
            <Route path="/SignUpPage" element={<SignUpPage />} />
            <Route path="/FindPassword" element={<FindPassword />} />
            <Route path="/BookTradingMainPage" element={<BookTradingMainPage />} />
            <Route path="/ProductRegistrationPage" element={<ProductRegistrationPage />} />
        </Routes>
    </Router>
  );
}

export default App;
