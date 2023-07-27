<template>
  <div class="main_container">
    <h2>Editovanje korisnika</h2>

    <div class="email_div">
      <label>Email:</label>
      <div>Originalna vrednost: {{ originalniKorisnik.email }}</div>
      <input v-model="izmenjeniKorisnik.email" type="text" />
    </div>

    <div class="name_div">
      <label>Ime:</label>
      <div>Originalna vrednost: {{ originalniKorisnik.ime }}</div>
      <input v-model="izmenjeniKorisnik.ime" type="text" />
    </div>

    <div class="prezime_div">
      <label>Prezime:</label>
      <div>Originalna vrednost: {{ originalniKorisnik.prezime }}</div>
      <input v-model="izmenjeniKorisnik.prezime" type="text" />
    </div>

    <div>
      <label>Tip:</label>
      <div>Originalna vrednost: {{ originalniKorisnik.tip }}</div>
      <select v-model="izmenjeniKorisnik.tip">
        <option value="Kontent kreator">Kontent kreator</option>
        <option value="Admin">Admin</option>
      </select>
    </div>

    <button class="sacuvaj_btn" @click="sacuvajIzmene">Sačuvaj izmene</button>
  </div>
</template>

<script>
export default {
  name: "EditovanjeKorisnika",
  data() {
    return {
      originalniKorisnik: {},
      izmenjeniKorisnik: {
        id: "",
        email: "",
        ime: "",
        prezime: "",
        tip: "",
      },
    };
  },
  methods: {
    sacuvajIzmene() {
      // Logika za čuvanje izmenjenih podataka korisnika
      console.log("Izmenjeni podaci korisnika:", this.izmenjeniKorisnik);
      this.$axios
        .put("/demo/api/korisnici", {
          id: this.originalniKorisnik.id,
          email:
            this.izmenjeniKorisnik.email != this.originalniKorisnik.email
              ? this.izmenjeniKorisnik.email
              : this.originalniKorisnik.email,
          ime:
            this.izmenjeniKorisnik.ime != this.originalniKorisnik.ime
              ? this.izmenjeniKorisnik.ime
              : this.originalniKorisnik.ime,
          prezime:
            this.izmenjeniKorisnik.prezime != this.originalniKorisnik.prezime
              ? this.izmenjeniKorisnik.prezime
              : this.originalniKorisnik.prezime,
          tip:
            this.izmenjeniKorisnik.tip != this.originalniKorisnik.tip
              ? this.izmenjeniKorisnik.tip
              : this.originalniKorisnik.tip,
        })
        .then((response) => console.log(response.data));
    },
  },
  created() {
    this.originalniKorisnik = JSON.parse(localStorage.getItem("korisnik"));
  },
};
</script>

<style>
.main_container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 1rem;
}

h2 {
  font-size: 24px;
  margin-bottom: 20px;
}

.sacuvaj_button {
  padding: 10px 20px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

.original-value {
  margin-top: 5px;
  color: #888;
}
</style>
