import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import api from '../Services/api';

export default function Login() {
  const navigate = useNavigate();
  const [form, setForm]     = useState({ email: '', password: '' });
  const [error, setError]   = useState('');
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(''); setLoading(true);
    try {
      const res = await api.post('/auth/login', form);
      localStorage.setItem('token', res.data.message);
      localStorage.setItem('email', form.email);
      navigate('/dashboard');
    } catch (err) {
      setError(err.response?.data?.message || err.response?.data || 'Login failed.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-root">
      <div className="auth-left">
        <div className="auth-left-brand">
          <div className="auth-left-icon">🏦</div>
          <div className="auth-left-brand-name">Vault<span>Bank</span></div>
        </div>
        <div className="auth-tagline">
          Banking you can <span>trust</span>, anywhere you are.
        </div>
        <p className="auth-sub">
          Manage your accounts, transfer funds, and track your finances — all in one secure place.
        </p>
        <div className="auth-features">
          <div className="auth-feature"><div className="auth-feature-dot" />256-bit SSL encryption</div>
          <div className="auth-feature"><div className="auth-feature-dot" />Instant fund transfers</div>
          <div className="auth-feature"><div className="auth-feature-dot" />24/7 account access</div>
        </div>
      </div>

      <div className="auth-right">
        <div className="auth-form-box">
          <h1>Welcome back</h1>
          <p className="subtitle">Sign in to your VaultBank account</p>

          {error && <div className="alert alert-error">⚠ {error}</div>}

          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <label>Email Address</label>
              <input name="email" type="email" placeholder="you@email.com"
                value={form.email} onChange={handleChange} required />
            </div>
            <div className="form-group">
              <label>Password</label>
              <input name="password" type="password" placeholder="Enter your password"
                value={form.password} onChange={handleChange} required />
            </div>
            <button className="btn btn-primary" type="submit" disabled={loading}>
              {loading && <span className="spinner" />}
              {loading ? 'Signing in...' : 'Sign In'}
            </button>
          </form>

          <div className="link-row">
            Don't have an account? <Link to="/register">Register here</Link>
          </div>
        </div>
      </div>
    </div>
  );
} 