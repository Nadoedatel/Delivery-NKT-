document.querySelectorAll('.card-product').forEach(card => {
    console.log('Скрипт загружен'); // Проверяем, загрузился ли JS
    card.addEventListener('click', function() {
        window.location.href = '/catalog';
    });
});
document.getElementById('registration').addEventListener('click', function(){
    window.location.href = '/registration';
})