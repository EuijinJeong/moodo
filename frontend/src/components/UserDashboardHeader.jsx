import React from "react";

const UserDashboardHeader = () => {
  const HandleToggle = () => {
    // 토글 동작을 수행하는 함수
  };

  return (
    <header>
      <div className="toggle-icon" onClick={HandleToggle}>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 24 24"
          width="24"
          height="24"
        >
          <path d="M0 0h24v24H0z" fill="none" />
          <path d="M4 18h16c.55 0 1-.45 1-1s-.45-1-1-1H4c-.55 0-1 .45-1 1s.45 1 1 1zm0-5h16c.55 0 1-.45 1-1s-.45-1-1-1H4c-.55 0-1 .45-1 1s.45 1 1 1zm0-5h16c.55 0 1-.45 1-1s-.45-1-1-1H4c-.55 0-1 .45-1 1s.45 1 1 1z" />
        </svg>
      </div>
    </header>
  );
};

export default UserDashboardHeader;
