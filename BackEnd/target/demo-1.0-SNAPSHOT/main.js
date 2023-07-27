import Vue from 'vue'
import App from './App.vue'
import router from './router'

new Vue({
    router,
    render: h => h(App)
}).$mount('#app')

new main({
    el: '#app',
    data: {
        nazivKategorije: '',
        opisKategorije: ''
    },
    methods: {
        dodajKategoriju() {
            // Ovde možete izvršiti željene radnje sa podacima iz polja
            console.log('Naziv kategorije:', this.nazivKategorije);
            console.log('Opis kategorije:', this.opisKategorije);
        }
    }
});