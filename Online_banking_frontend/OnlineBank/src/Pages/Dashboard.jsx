import { useNavigate } from 'react-router-dom';
import Navbar from '../components/Navbar';

export default function Dashboard() {
  const navigate = useNavigate();
  const email    = localStorage.getItem('email') || 'User';
  const username = email.split('@')[0];

  const cards = [
    { icon: '➕', label: 'Create Account',  desc: 'Open a new savings or current account instantly.', path: '/create-account'  },
    { icon: '🏦', label: 'My Accounts',     desc: 'View your account balance and details.',            path: '/account-details' },
    { icon: '💸', label: 'Transfer Money',  desc: 'Send funds securely to any VaultBank account.',     path: '/transfer'        },
  ];

  return (
    <>
      <Navbar />
      <div className="page-wrapper">
        <div className="dashboard-hero">
          <div className="hero-text">
            <h2>Good day, {username} 👋</h2>
            <p>Welcome to your VaultBank dashboard. What would you like to do today?</p>
          </div>
          <div className="hero-badge">
            <div className="hero-badge-label">Secure Session</div>
            <div className="hero-badge-value">🔒 Active</div>
          </div>
        </div>

        <div className="page-header">
          <h1>Quick Actions</h1>
          <p>Select an option below to get started</p>
        </div>

        <div className="dashboard-grid">
          {cards.map((c) => (
            <div key={c.path} className="dash-card" onClick={() => navigate(c.path)}>
              <div className="dash-card-icon">{c.icon}</div>
              <div className="dash-card-label">{c.label}</div>
              <div className="dash-card-desc">{c.desc}</div>
              <div className="dash-card-arrow">Get started →</div>
            </div>
          ))}
        </div>
      </div>
    </>
  );
}