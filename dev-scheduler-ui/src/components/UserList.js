import React, { useState, useEffect } from 'react';
import axiosInstance from '../axiosConfig';
import { Link } from 'react-router-dom';
import { Container, Typography, List, ListItem, ListItemText, Button } from '@mui/material';

function UserList() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    axiosInstance.get('/users')
      .then(response => setUsers(response.data))
      .catch(error => console.error('Error fetching users:', error));
  }, []);

  return (
    <Container maxWidth="sm">
      <Typography variant="h4" component="h1" gutterBottom>
        User List
      </Typography>
      <Button variant="contained" color="primary" component={Link} to="/add-user">
        Add User
      </Button>
      <List>
        {users.map(user => (
          <ListItem key={user.id}>
            <ListItemText primary={`${user.name} - ${user.email}`} />
            <Button variant="outlined" color="secondary" component={Link} to={`/users/${user.id}/timeoffs`}>
              Add Time-Off
            </Button>
          </ListItem>
        ))}
      </List>
    </Container>
  );
}

export default UserList;