import React, { useState } from "react";
import "../css/password_reset.css";

const PasswordReset = () => {
  const [email, setEmail] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [emailSent, setEmailSent] = useState(false);
  const [resetSuccessful, setResetSuccessful] = useState(false);

  const handleEmailSubmit = (e) => {
    e.preventDefault();
    // 여기에서 이메일 전송 및 이메일 확인 로직을 추가하세요.
    // 임시로 이메일이 전송된 것으로 간주하고 다음 단계로 진행합니다.
    setEmailSent(true);
  };

  const handleResetSubmit = (e) => {
    e.preventDefault();
    if (newPassword !== confirmPassword) {
      alert("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
      return;
    }
    // 여기에서 비밀번호 재설정 로직을 추가하세요.
    // 비밀번호 재설정이 성공적으로 완료되면 setResetSuccessful(true)를 호출하세요.
    setResetSuccessful(true);
    setNewPassword("");
    setConfirmPassword("");
  };

  if (resetSuccessful) {
    return (
      <div>
        <h2>Password Reset Successful!</h2>
      </div>
    );
  }

  return (
    <div>
      {!emailSent ? (
        <section id="find_password">
          <div className="form_inner">
            <form onSubmit={handleEmailSubmit}>
              <h2>비밀번호 재설정</h2>
              <label id="email-label" htmlFor="email">Email:</label>
              <input
                type="email"
                id="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="이메일 양식에 맞게 입력하세요"
                required
              />
              <div id="button-container">
                <button type="submit">Send Reset Link</button>
              </div>
            </form>
          </div>
        </section>
      ) : (
          <section id="reset_password" >
            <div className="form_inner">
              <form onSubmit={handleResetSubmit}>
                <h2>Reset Your Password</h2>
                <label htmlFor="newPassword">New Password:</label>
                <input
                    type="password"
                    id="newPassword"
                    value={newPassword}
                    onChange={(e) => setNewPassword(e.target.value)}
                    required
                />
                <label htmlFor="confirmPassword">Confirm Password:</label>
                <input
                    type="password"
                    id="confirmPassword"
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)}
                    required
                />
                <button type="submit">Reset Password</button>
              </form>
            </div>

          </section>

      )}
    </div>
  );
};

export default PasswordReset;
