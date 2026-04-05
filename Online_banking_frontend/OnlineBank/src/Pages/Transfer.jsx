import { useState } from 'react';
import Navbar from '../components/Navbar';
import api from '../Services/api';

export default function Transfer() {
  const [form, setForm]       = useState({ fromAccount: '', toAccount: '', amount: '' });
  const [error,   setError]   = useState('');
  const [success, setSuccess] = useState('');
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(''); setSuccess(''); setLoading(true);
    try {
      const res = await api.post('/transfer', {
        fromAccount: form.fromAccount,
        toAccount:   form.toAccount,
        amount:      parseFloat(form.amount),
      });
      setSuccess(res.data || 'Transaction Successful!');
      setForm({ fromAccount: '', toAccount: '', amount: '' });
    } catch (err) {
      setError(err.response?.data || 'Transfer failed. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <Navbar />
      <div className="page-wrapper">
        <div className="page-header">
          <h1>Transfer Money</h1>
          <p>Send funds securely to any VaultBank account</p>
        </div>

        <div className="content-grid">
          <div className="content-card">
            <h2>Transfer Details</h2>
            <p className="subtitle">Fill in the details below to initiate a transfer</p>

            {error   && <div className="alert alert-error">⚠ {error}</div>}
            {success && <div className="alert alert-success">✓ {success}</div>}

            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <label>From Account (Your Account ID)</label>
                <input name="fromAccount" type="text" placeholder="e.g. ACC_01"
                  value={form.fromAccount} onChange={handleChange} required />
              </div>
              <div className="form-group">
                <label>To Account (Receiver Account ID)</label>
                <input name="toAccount" type="text" placeholder="e.g. ACC_02"
                  value={form.toAccount} onChange={handleChange} required />
              </div>
              <div className="form-group">
                <label>Amount (₹)</label>
                <input name="amount" type="number" placeholder="e.g. 500" min="1" step="0.01"
                  value={form.amount} onChange={handleChange} required />
              </div>
              <button className="btn btn-primary" type="submit" disabled={loading}>
                {loading && <span className="spinner" />}
                {loading ? 'Processing...' : 'Send Money'}
              </button>
            </form>
          </div>

          <div className="info-panel">
            <h3>Transfer Guidelines</h3>
            <div className="info-list">
              <div className="info-item"><span className="info-item-icon">🔐</span>You can only send from your own accounts.</div>
              <div className="info-item"><span className="info-item-icon">⚡</span>Transfers are processed instantly.</div>
              <div className="info-item"><span className="info-item-icon">💰</span>Ensure sufficient balance before transferring.</div>
              <div className="info-item"><span className="info-item-icon">🚫</span>Sender and receiver accounts cannot be the same.</div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}