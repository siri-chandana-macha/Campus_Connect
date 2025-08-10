document.getElementById('imageUpload').addEventListener('change', function(event) {
  const file = event.target.files[0];
  if (file && file.size <= 5 * 1024 * 1024) {
    const reader = new FileReader();
    reader.onload = function(e) {
      const uploadedImage = e.target.result;
      document.getElementById('profile-image').src = uploadedImage;
      document.getElementById('nav-profile-pic').src = uploadedImage;
    };
    reader.readAsDataURL(file);
  } else {
    alert("Please upload an image smaller than 5 MB.");
  }
});

function triggerUpload() {
  document.getElementById('imageUpload').click();
}

function saveProfile(event) {
  event.preventDefault();
  const username = document.getElementById('username').value.trim();
  if (username) {
    document.getElementById('nav-username').innerText = username;
    alert('Profile saved!');
  } else {
    alert('Please enter a username.');
  }
  return false;
}
