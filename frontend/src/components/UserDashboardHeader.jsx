import React from 'react';
import '../css/user_dashboard_header.css'; // Include CSS for styling

const UserDashboardHeader = () => {
  return (
      <header className="dashboard-header">
        <div className="logo">모두의 전공책</div>
        <input type="text" className="search-bar" placeholder="전공책 이름, 출판사명 입력" />
        <nav className="nav-links">
          <a href="/sell">판매하기</a>
          <a href="/profile">내상점</a>
          <a href="/messages">모두톡</a>
        </nav>
      </header>
  );
}

export default UserDashboardHeader;

