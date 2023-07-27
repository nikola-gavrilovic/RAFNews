<template>
  <div class="create-category">
    <h2>Napravi kategoriju</h2>
    <form @submit.prevent="kreirajKategoriju">
      <div class="form-group">
        <label for="ime">Ime:</label>
        <input type="text" id="ime" v-model="ime" />
      </div>
      <div class="form-group">
        <label for="opis">Opis:</label>
        <textarea id="opis" v-model="opis"></textarea>
      </div>
      <div class="form-actions">
        <button type="submit" @click="kreirajKategoriju(ime, opis)">
          Kreiraj kategoriju
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import Axios from "axios";

export default {
  data() {
    return {
      ime: "",
      opis: "",
      kategorije: [],
    };
  },
  methods: {
    kreirajKategoriju(i, o) {
      // const headers = {
      //   Authorization: `Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkcnVnaUtvcmlzbmlrQGdtYWlsLmNvbSIsInJvbGUiOiJBZG1pbiIsImV4cCI6MTY4NjIzODQxMSwiaWF0IjoxNjg2MTUyMDExfQ.EXGNxKnshB1ujd4Zx3iro0ES_IAmsoW6xGOeMbELq7I`,
      //   "Content-Type": "application/json",
      // };
      Axios.post(
        "/demo/api/kategorije",
        {
          ime: i,
          opis: o,
        }
        // { headers: headers }
      )
        .then((response) => {
          console.log(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style scoped>
.create-category {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  border: 1px solid #ccc;
  border-radius: 4px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.create-category h2 {
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 20px;
  width: 100%;
}

label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
}

input[type="text"],
textarea {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.form-actions {
  text-align: right;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

button {
  padding: 8px 16px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>
