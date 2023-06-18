function getAuthorization() {
  let user = JSON.parse(localStorage.getItem('user'));
  
  if (user && user.accessToken) {
    return 'Bearer ' + user.accessToken;
  } else {
    return '';
  }
}
function authHeader() {
  let user = JSON.parse(localStorage.getItem('user'));

  if (user && user.accessToken) {
    return { Authorization: 'Bearer ' + user.accessToken };
  } else {
    return {};
  }
}


export {authHeader, getAuthorization}