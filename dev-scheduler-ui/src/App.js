import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import UserList from './components/UserList';
import UserForm from './components/UserForm';
import TimeOffForm from './components/TimeOffForm';

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<UserList />} />
          <Route path="/add-user" element={<UserForm />} />
          <Route path="/users/:id/timeoffs" element={<TimeOffForm />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;