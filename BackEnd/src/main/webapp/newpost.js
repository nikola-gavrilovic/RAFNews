const btn = document.getElementById('new-post-btn');


const submitbtn = document.getElementById('submit-btn-post');

btn.addEventListener('click', () => {
  const form = document.getElementById('subject-form');

  if (form.style.visibility === 'hidden') {
    form.style.visibility = 'visible';
    
       submitbtn.addEventListener('click', () => {
    	form.style.visibility = 'hidden';
       });
    
  } else {
	  form.style.visibility = 'hidden';
  }
  
});