import { useState, useEffect } from 'react';
import Navbar from '../components/Navbar';
import api from '../Services/api';

export default function AccountDetails() {
  const [accounts, setAccounts] = useState([]);
  const [error,    setError]    = useState('');
  const [loading,  setLoading]  = useState(true);

  useEffect(() => {
    api.get('/accounts/my-accounts')
      .then(res => {
        const data = res.data;
        if (Array.isArray(data)) {
          setAccounts(data);
        } else if (data && typeof data === 'object') {
          setAccounts([data]);
        } else {
          setAccounts([]);
        }
      })
      .catch(err => setError(err.response?.data || 'Failed to fetch accounts.'))
      .finally(() => setLoading(false));
  }, []);

  const formatBalance = (balance) => {
    const num = parseFloat(balance);
    if (isNaN(num)) return '0.00';
    return num.toLocaleString('en-IN', { minimumFractionDigits: 2 });
  };

  return (
    <>
      <Navbar />
      <div className="page-wrapper">
        <div className="page-header">
          <h1>My Accounts</h1>
          <p>Overview of all your VaultBank accounts</p>
        </div>

        {loading && <div className="alert alert-success">Loading your accounts...</div>}
        {error   && <div className="alert alert-error">⚠ {error}</div>}

        {!loading && !error && accounts.length === 0 && (
          <div className="alert alert-error">
            No accounts found. Create one from the dashboard.
          </div>
        )}

        <div className="accounts-grid">
          {accounts.map((acc, i) => (
            <div key={acc.account_id || i} className="account-card">
              <div className="account-card-header">
                <div className="account-card-title">Account {i + 1}</div>
                <div className="account-badge">{acc.currency || 'INR'}</div>
              </div>
              <div className="info-row">
                <span className="info-label">Account ID</span>
                <span className="info-value">{acc.account_id || 'N/A'}</span>
              </div>
              <div className="info-row">
                <span className="info-label">Account Number</span>
                <span className="info-value">{acc.account_number || 'N/A'}</span>
              </div>
              <div className="info-row">
                <span className="info-label">Balance</span>
                <span className="balance-value">₹ {formatBalance(acc.balance)}</span>
              </div>
            </div>
          ))}
        </div>
      </div>
    </>
  );
}