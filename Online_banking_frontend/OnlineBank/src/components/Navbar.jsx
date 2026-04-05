import { useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';

export default function Navbar() {
  const navigate = useNavigate();
  const location = useLocation();
  const email    = localStorage.getItem('email') || '';
  const [open, setOpen] = useState(false);

  const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('email');
    navigate('/login');
  };

  const links = [
    { label: 'Dashboard',      path: '/dashboard'       },
    { label: 'My Accounts',    path: '/account-details' },
    { label: 'Create Account', path: '/create-account'  },
    { label: 'Transfer',       path: '/transfer'        },
  ];

  return (
    <>
      <nav className="navbar">
        <div className="nav-brand" onClick={() => navigate('/dashboard')}>
          <div className="nav-brand-icon">🏦</div>
          <div className="nav-brand-name">Vault<span>Bank</span></div>
        </div>

        <div className="nav-links">
          {links.map((l) => (
            <button key={l.path}
              className={`nav-link ${location.pathname === l.path ? 'active' : ''}`}
              onClick={() => navigate(l.path)}>
              {l.label}
            </button>
          ))}
        </div>

        <div className="nav-right">
          <span className="nav-email">{email}</span>
          <button className="nav-logout" onClick={logout}>Logout</button>
        </div>

        <button className="nav-hamburger" onClick={() => setOpen(!open)}>
          <span /><span /><span />
        </button>
      </nav>

      <div className={`nav-mobile-menu ${open ? 'open' : ''}`}>
        {links.map((l) => (
          <button key={l.path} className="nav-mobile-link"
            onClick={() => { navigate(l.path); setOpen(false); }}>
            {l.label}
          </button>
        ))}
        <button className="nav-mobile-link" style={{ color: '#fca5a5' }} onClick={logout}>
          Logout
        </button>
      </div>
    </>
  );
}