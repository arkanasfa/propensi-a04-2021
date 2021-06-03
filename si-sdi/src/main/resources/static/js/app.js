const imgDiv = document.querySelector('.profile-pic-div');
const img = document.querySelector('#photoo');
const file = document.querySelector('#filee');
const uploadBtn = document.querySelector('#uploadBtn');


//hover on

imgDiv.addEventListener('mouseenter', function ()
{
    uploadBtn.style.display="block";
});

//hover out
imgDiv.addEventListener('mouseleave', function ()
{
    uploadBtn.style.display='none';
});

file.addEventListener('change', function () {
    const choosedFile = this.files[0];
    if(choosedFile){
        // const reader = new FileReader();
        reader.addEventListener('load', function (){
            img.setAttribute('src', window.URL.createObjectURL(uploader.files[0]) );
        });
        // reader.readAsDataURL(choosedFile);
    }
});