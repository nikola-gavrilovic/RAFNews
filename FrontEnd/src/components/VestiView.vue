<template>
  <div>
    <h2>Vesti</h2>
    <table>
      <thead>
        <tr>
          <th>Naslov</th>
          <th>Autor</th>
          <th>Broj poseta</th>
          <th>Vreme kreiranja</th>
          <th>Tekst</th>
          <th>Tagovi</th>
          <th v-if="isAdmin">Akcije</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="vest in vesti" :key="vest.id">
          <td>
            <router-link :to="`/otvoriVest/${vest.id}`" class="vest_dugme">
              <a href="#" @click="getVestById(vest.id)">
                {{ vest.naslov }}
              </a></router-link
            >
          </td>
          <td>{{ vest.autor }}</td>
          <td>{{ vest.brojPoseta }}</td>
          <td>{{ vest.vremeKreiranja }}</td>
          <td>{{ vest.tekst }}</td>
          <td>
            <ul>
              <li v-for="tag in vest.tagovi" :key="tag.id">{{ tag.tag }}</li>
            </ul>
          </td>
          <td v-if="isAdmin">
            <button @click="obrisiVest(vest.id)">Obriši</button>
          </td>
        </tr>
      </tbody>
    </table>
    <router-link to="/napraviVest" class="dugme">Dodaj vest</router-link>
  </div>
</template>

<script>
export default {
  data() {
    return {
      vesti: [],
      vest: {},
      isAdmin: true, // Postavite na true ako je trenutni korisnik tipa "Admin"
    };
  },
  methods: {
    obrisiVest(vestId) {
      this.$axios.delete("/demo/api/vesti/" + vestId).then((response) => {
        this.vesti = response.data;
      });
    },
    getVestById(id) {
      this.$axios.get("/demo/api/vesti/" + id).then((response) => {
        this.vest = response.data;
      });
    },
  },
  created() {
    this.vesti = null;
    this.$axios.get("/demo/api/vesti").then((response) => {
      this.vesti = response.data;
    });
  },
};
</script>

<style>
table {
  width: 100%;
  border-collapse: collapse;
}
button {
  padding: 4px 8px;
  background-color: red;
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 5px;
}
th,
td {
  border: 1px solid black;
  padding: 8px;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin-right: 5px;
}
.dugme {
  display: inline-block;
  padding: 8px 16px;
  background-color: #337ab7;
  color: white;
  text-decoration: none;
  border-radius: 4px;
  margin-top: 16px;
}
.vest_dugme a {
  text-decoration: none;
  color: black;
  font-weight: bold;
}
.vest_dugme a:hover {
  color: gray;
}
</style>
