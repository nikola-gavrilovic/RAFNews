<template>
  <div class="kategorije_div">
    <table>
      <thead>
        <tr>
          <th>Ime</th>
          <th>Opis</th>
          <th>Akcija</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="kategorija in kategorije" :key="kategorija.id">
          <td>{{ kategorija.ime }}</td>
          <td>{{ kategorija.opis }}</td>
          <td>
            <button @click="deleteCategory(kategorija.id)">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
    <p v-if="errorMessage" class="error-message">
      {{ "Postoji vest sa tom kategorijom" }}
    </p>
    <router-link to="/napraviKategoriju" class="new-category-button"
      >Nova kategorija</router-link
    >
  </div>
</template>

<script>
export default {
  data() {
    return {
      kategorije: [],
      errorMessage: null,
    };
  },
  methods: {
    deleteCategory(kategorija) {
      this.$axios
        .delete("/demo/api/kategorije/" + kategorija)
        .then((response) => {
          this.kategorije = response.data;
        })
        .catch((error) => {
          this.errorMessage = error.message;
          console.log(error);
        });
      window.location.reload();
    },
  },
  created() {
    this.$axios.get("/demo/api/kategorije").then((response) => {
      this.kategorije = response.data;
    });
  },
};
</script>
<style>
.kategorije_div {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.category-table {
  width: 100%;
  border-collapse: collapse;
}

.category-table th,
.category-table td {
  padding: 8px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

.category-table th {
  background-color: #f2f2f2;
}

.delete-button {
  background-color: #dc3545;
  color: #fff;
  border: none;
  padding: 8px 12px;
  border-radius: 4px;
  cursor: pointer;
}

.delete-button:hover {
  background-color: #c82333;
}

.error-message {
  color: red;
  margin-top: 10px;
}
.new-category-button {
  display: block;
  margin-top: 10px;
  padding: 8px 16px;
  background-color: #007bff;
  color: #fff;
  text-decoration: none;
  border-radius: 4px;
  text-align: center;
  width: 20%;
}

.new-category-button:hover {
  background-color: #0056b3;
}
</style>
