<template>
  <div>
    <h2>Korisnici</h2>
    <table>
      <thead>
        <tr>
          <th>Ime</th>
          <th>Prezime</th>
          <th>Tip</th>
          <th>Aktivan</th>
          <th v-if="isAdmin">Akcije</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="korisnik in korisnici" :key="korisnik.id">
          <td>{{ korisnik.ime }}</td>
          <td>{{ korisnik.prezime }}</td>
          <td>{{ korisnik.tip }}</td>
          <td>{{ korisnik.aktivan }}</td>
          <td v-if="true">
            <button @click="editKorisnika(korisnik)">Edit Korisnika</button>
            <button @click="obrisiKorisnika(korisnik)">
              Aktiviraj/Deaktiviraj
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "KorisnikViewComponent",
  props: {
    msg: String,
    korisnik: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      korisnici: [],
      isAdmin: false,
    };
  },
  methods: {
    obrisiKorisnika(korisnik) {
      this.$axios
        .put("/demo/api/korisnici/status", {
          id: korisnik.id,
          email: korisnik.email,
          ime: korisnik.ime,
          prezime: korisnik.prezime,
          tip: korisnik.tip,
        })
        .then((response) => {
          this.korisnici = response.data;
        })
        .catch(() => {
          alert("Korisnik je Admin!");
        });
      window.location.reload();
    },
    editKorisnika(korisnik) {
      // Napravi novu stranicu za editovanje korisnika, korisnik moze da menja sve podatke osim ID, tip da bude dropdown
      localStorage.setItem("korisnik", JSON.stringify(korisnik));
      this.$router.push("/editKorisnika");
    },
  },
  created() {
    this.korisnici = null;
    this.$axios
      .get("/demo/api/korisnici")
      .then((response) => {
        this.korisnici = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
}

th,
td {
  border: 1px solid black;
  padding: 8px;
}

button {
  padding: 4px 8px;
  background-color: red;
  color: white;
  border: none;
  cursor: pointer;
  width: 50%;
  border-radius: 5px;
}
button :hover {
  background-color: antiquewhite;
}
</style>
