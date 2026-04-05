import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Login from "./Pages/login";
import Register from "./Pages/register";
import Dashboard from "./Pages/Dashboard";
import CreateAccount from "./Pages/CreateAccount";
import AccountDetails from "./Pages/AccountDetails";
import Transfer from "./Pages/Transfer";

const PrivateRoute=({children})=>{
  const token=localStorage.getItem("token");
  return token ? children : <Navigate to="/login"/>;
};

function App() {
  return (
    <BrowserRouter>
      <Routes>

        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="/login" element={<Login/>}/>
        <Route path="/register" element={<Register />} />
        <Route path="/dashboard" element={<PrivateRoute><Dashboard /></PrivateRoute>} />
        <Route path="/create-account" element={<PrivateRoute><CreateAccount /></PrivateRoute>} />
        <Route path="/account-details" element={<PrivateRoute><AccountDetails /></PrivateRoute>} />
        <Route path="/transfer" element={<PrivateRoute><Transfer /></PrivateRoute>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;