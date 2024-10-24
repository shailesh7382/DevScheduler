import React, { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { TextField, Button, Container, Typography } from '@mui/material';
import axiosInstance from '../axiosConfig';

function TimeOffForm() {
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const { userId } = useParams(); // Get userId from URL parameters

  const handleSubmit = (event) => {
    event.preventDefault();
    setError(null); // Reset error state
    axiosInstance.post(`/users/${userId}/timeoffs`, { startDate, endDate })
      .then(() => navigate('/'))
      .catch(error => {
        console.error('Error creating time-off:', error);
        setError('Failed to create time-off. Please try again.');
      });
  };

  return (
    <Container maxWidth="sm">
      <Typography variant="h4" component="h1" gutterBottom>
        Add Time-Off
      </Typography>
      <form onSubmit={handleSubmit}>
        <TextField
          label="Start Date"
          type="date"
          value={startDate}
          onChange={(e) => setStartDate(e.target.value)}
          fullWidth
          margin="normal"
          InputLabelProps={{
            shrink: true,
          }}
        />
        <TextField
          label="End Date"
          type="date"
          value={endDate}
          onChange={(e) => setEndDate(e.target.value)}
          fullWidth
          margin="normal"
          InputLabelProps={{
            shrink: true,
          }}
        />
        {error && <Typography color="error">{error}</Typography>}
        <Button type="submit" variant="contained" color="primary" fullWidth>
          Add Time-Off
        </Button>
      </form>
    </Container>
  );
}

export default TimeOffForm;