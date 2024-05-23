import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import MainPage from './pages/MainPage';
import SignUpPage from "./pages/SignUpPage";
import FindPassword from "./pages/FindPassword";
function App() {
  return (
    <Router>
        <Routes>
            <Route path="/" element={<MainPage />} /> 
            <Route path="/SignUpPage" element={<SignUpPage />} />
            <Route path="/FindPassword" element={<FindPassword />} />
        </Routes>
    </Router>
  );
}

export default App;
