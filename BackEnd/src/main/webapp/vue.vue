<template>
    <div>
        <header></header>
        <main class="main_m">
            <div class="nova_kat_div">
                <button class="dodaj_btn" @click="dodajKategoriju">
                    Dodaj kategoriju
                </button>
                <button class="dodaj_btn" @click="dodajVest">
                    Dodaj vest
                </button>
            </div>
        </main>
        <footer></footer>
    </div>
</template>

<script>
export default {
    methods: {
        dodajKategoriju() {
            // Logika za dodavanje kategorije
        },
        dodajVest() {
            // Logika za dodavanje vesti
        },
    },
};
</script>

<style scoped>
/* Vaš CSS stil */
</style>