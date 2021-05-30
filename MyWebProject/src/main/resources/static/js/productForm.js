const productsControl = new ProductsController();

var storeImage;

//When user clicks on 'save Item' , calls API to add items to the database
//add an 'onsubmit' event listener for productForm to add a product
newItemForm.addEventListener('submit',(event) =>{
        //prevent default action
        event.preventDefault();
        //select the inputs
        const newItemNameInput = document.querySelector('#newItemNameInput');
        const newItemDescription= document.querySelector('#newItemDescription');
        const newItemImageUrl= document.querySelector('#imagefile');
        const newItemStyle= document.querySelector('#newItemStyle');
        const newItemPrice= document.querySelector('#newItemPrice');

        /*
         Do the validation code here
        */

        // Get the values of the inputs - variable names to be same as MySQL columns
        const name = newItemNameInput.value;
        const description =  newItemDescription.value;
        // users/user/Desktop/***.png
        const imageUrl ="images/" + newItemImageUrl.value.replace("C:\\fakepath\\","");
        const style = newItemStyle.value;
        const price = newItemPrice.value;

        //Clear the form
        newItemNameInput.value='';
        newItemDescription.value='';
        newItemImageUrl.value='';
        newItemStyle.value='';
        newItemPrice.value ='';

        //Add the task to the task manager
        productsControl.addItem(name, description, imageUrl, style, price, storeImage);


});

//select file input
const input = document.querySelector ('#imagefile')

// add event listener
input.addEventListener('change',()=>{
    storeImage = input.files[0]; // return the file object of the first (one & only) file
    console.log("input : " + storeImage);
    //uploadFile(input.files[0]);
});

