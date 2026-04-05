import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Navbar from '../components/Navbar';
import api from '../Services/api';

export default function CreateAccount() {
  const navigate = useNavigate();
  const [balance, setBalance] = useState('');
  const [error,   setError]   = useState('');
  const [success, setSuccess] = useState('');
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(''); setSuccess(''); setLoading(true);
    try {
      const res = await api.post('/accounts', { balance: parseFloat(balance) });
      setSuccess(`Account created! Account No: ${res.data.account_id}`);
    } catch (err) {
      setError(err.response?.data || 'Failed to create account.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <Navbar />
      <div className="page-wrapper">
        <div className="page-header">
          <h1>Open New Account</h1>
          <p>Create a new bank account linked to your profile</p>
        </div>

        <div className="content-grid">
          <div className="content-card">
            <h2>Account Details</h2>
            <p className="subtitle">Enter your initial deposit to get started</p>

            {error   && <div className="alert alert-error">⚠ {error}</div>}
            {success && <div className="alert alert-success">✓ {success}</div>}

            {!success && (
              <form onSubmit={handleSubmit}>
                <div className="form-group">
                  <label>Initial Deposit (₹)</label>
                  <input type="number" placeholder="e.g. 10000" min="0" step="0.01"
                    value={balance} onChange={(e) => setBalance(e.target.value)} required />
                </div>
                <button className="btn btn-primary" type="submit" disabled={loading}>
                  {loading && <span className="spinner" />}
                  {loading ? 'Creating...' : 'Create Account'}
                </button>
              </form>
            )}

            {success && (
              <button className="btn btn-outline" onClick={() => navigate('/dashboard')}
                style={{ marginTop: 8 }}>
                Back to Dashboard
              </button>
            )}
          </div>

          <div className="info-panel">
            <h3>What you get</h3>
            <div className="info-list">
              <div className="info-item"><span className="info-item-icon">🔒</span>Your account is secured with bank-grade encryption.</div>
              <div className="info-item"><span className="info-item-icon">⚡</span>Instant activation — start transacting immediately.</div>
              <div className="info-item"><span className="info-item-icon">💳</span>Unique account number generated automatically.</div>
              <div className="info-item"><span className="info-item-icon">🌐</span>Access your account 24/7 from anywhere.</div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}