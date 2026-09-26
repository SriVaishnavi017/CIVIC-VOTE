document.getElementById('loginBtn')?.addEventListener('click', () => {
  const token = 'demo-jwt-token-for-academic-review';
  alert('Demo login simulated. In the full implementation, the frontend will receive a JWT from the Auth Service.');
  console.log('JWT placeholder:', token);
});
