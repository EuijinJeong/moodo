import React, {useState} from 'react';
import '../css/user_dashboard_header.css';
import {Link} from "react-router-dom";
import ProductRegistrationPage from "../pages/ProductRegistrationPage"; // Include CSS for styling


const UserDashboardHeader = () => {
    const [show, setShow] = useState(false);
    const toggleMenu = () => {
        setShow((prevShow) => !prevShow);
    };
  return (
      <header id="header" role="banner">
          <div className="header_inner">
              <div className="header_logo">
                  <a href="/BookTradingMainPage">모두의 전공책</a></div>
              <input type="text" className="search-bar" placeholder="전공책 이름, 출판사명 입력" />
              <nav className={`header_nav ${show ? "show" : ""}`} role="navigation">
                  <ul>
                      <li><Link to="/ProductRegistrationPage">판매하기</Link></li>
                      <li><a href="/">내상점</a></li>
                      <li><a href="/">모두톡</a></li>
                  </ul>
              </nav>
              <div
                  className="header_nav_mobile"
                  id="headerToggle"
                  aria-controls="primary-menu"
                  aria-expanded={show ? "true" : "false"}
                  role="button"
                  tabindex="0"
                  onClick={toggleMenu}
              >
                  <span></span>
              </div>
          </div>
      </header>
  );
}

export default UserDashboardHeader;

