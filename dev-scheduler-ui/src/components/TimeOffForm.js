import React, { useState } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';

function TimeOffForm() {
  const { id } = useParams();
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [reason, setReason] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    axios.post(`/users/${id}/timeoffs`, { startDate, endDate, reason })
      .then(() => navigate('/'))
      .catch(error => console.error('Error adding time-off:', error));
  };

  return (
    <div>
      <h1>Add Time-Off</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Start Date:</label>
          <input type="date" value={startDate} onChange={(e) => setStartDate(e.target.value)} />
        </div>
        <div>
          <label>End Date:</label>
          <input type="date" value={endDate} onChange={(e) => setEndDate(e.target.value)} />
        </div>
        <div>
          <label>Reason:</label>
          <input type="text" value={reason} onChange={(e) => setReason(e.target.value)} />
        </div>
        <button type="submit">Add Time-Off</button>
      </form>
    </div>
  );
}

export default TimeOffForm;