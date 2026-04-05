import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import api from '../Services/api';

export default function Register() {
  const navigate = useNavigate();
  const [form, setForm]       = useState({ name: '', email: '', password: '' });
  const [error, setError]     = useState('');
  const [success, setSuccess] = useState('');
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(''); setSuccess(''); setLoading(true);
    try {
      await api.post('/auth/register', form);
      setSuccess('Account created! Redirecting to login...');
      setTimeout(() => navigate('/login'), 1800);
    } catch (err) {
      setError(err.response?.data?.message || err.response?.data || 'Registration failed.');
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
          Your finances, <span>secured</span> and simplified.
        </div>
        <p className="auth-sub">
          Join thousands of customers who trust VaultBank for their everyday banking needs.
        </p>
        <div className="auth-features">
          <div className="auth-feature"><div className="auth-feature-dot" />Free account opening</div>
          <div className="auth-feature"><div className="auth-feature-dot" />Zero hidden charges</div>
          <div className="auth-feature"><div className="auth-feature-dot" />Instant account activation</div>
        </div>
      </div>

      <div className="auth-right">
        <div className="auth-form-box">
          <h1>Create account</h1>
          <p className="subtitle">Join VaultBank in seconds</p>

          {error   && <div className="alert alert-error">⚠ {error}</div>}
          {success && <div className="alert alert-success">✓ {success}</div>}

          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <label>Full Name</label>
              <input name="name" type="text" placeholder="Your name"
                value={form.name} onChange={handleChange} required />
            </div>
            <div className="form-group">
              <label>Email Address</label>
              <input name="email" type="email" placeholder="you@email.com"
                value={form.email} onChange={handleChange} required />
            </div>
            <div className="form-group">
              <label>Password</label>
              <input name="password" type="password" placeholder="Min. 8 characters"
                value={form.password} onChange={handleChange} required />
            </div>
            <button className="btn btn-primary" type="submit" disabled={loading}>
              {loading && <span className="spinner" />}
              {loading ? 'Creating account...' : 'Create Account'}
            </button>
          </form>

          <div className="link-row">
            Already have an account? <Link to="/login">Sign in</Link>
          </div>
        </div>
      </div>
    </div>
  );
}