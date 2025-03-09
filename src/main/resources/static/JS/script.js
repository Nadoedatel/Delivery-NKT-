document.querySelectorAll('.card-product').forEach(card => {
    card.addEventListener('click', function() {
        let shopId = this.getAttribute('data-shop-id');
        if (shopId) {
            window.location.href = `/product/${shopId}`;
        }
    });
});

document.getElementById('registration').addEventListener('click', function(){
    window.location.href = '/registration';
})