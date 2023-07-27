<template>
  <div class="vest-container">
    <h2 class="naslov">{{ vest.naslov }}</h2>
    <p class="autor">Autor: {{ vest.autor }}</p>
    <p class="datum">Datum kreiranja: {{ vest.vremeKreiranja }}</p>
    <p class="tekst">{{ vest.tekst }}</p>
    <ul class="tagovi">
      <li v-for="tag in vest.tagovi" :key="tag.id" class="tag">
        {{ tag.tag }}
      </li>
    </ul>
    <ul class="komentari">
      <li
        v-for="komentar in vest.komentari"
        :key="komentar.id"
        class="komentar"
      >
        {{ komentar }}
      </li>
    </ul>
    <div class="dodaj-komentar">
      <input
        type="text"
        v-model="noviKomentar"
        placeholder="Unesite komentar"
        class="komentar-input"
      />
      <button @click="dodajKomentar(vest.id)" class="dodaj-btn">
        Dodaj komentar
      </button>
    </div>
  </div>
</template>

<script>
export default {
  props: ["id"],
  data() {
    return {
      vest: null,
      noviKomentar: "",
      autor: localStorage.getItem("imeprezime"),
    };
  },
  created() {
    this.getVestById(this.id);
  },
  methods: {
    getVestById(id) {
      this.$axios
        .get("/demo/api/vesti/" + id, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("jwt"),
          },
        })
        .then((response) => {
          this.vest = response.data;
        });
    },
    dodajKomentar(id) {
      console.log("Dodavanje komentara:", this.noviKomentar);
      // this.vest.komentari.push(this.noviKomentar);
      this.$axios
        .post("/demo/api/vesti/add-comment/" + id, {
          tekst: this.noviKomentar,
          autor: this.autor,
        })
        .then((response) => {
          this.tagovi = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
      this.noviKomentar = "";
      window.location.reload();
    },
  },
};
</script>

<style>
.vest-container {
  margin: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.naslov {
  font-size: 24px;
  font-weight: bold;
}

.autor {
  font-style: italic;
  color: #888;
}

.datum {
  color: #888;
}

.tekst {
  margin-top: 10px;
}

.tagovi {
  list-style-type: none;
  margin: 10px 0;
  padding: 0;
}

.tag {
  display: inline-block;
  margin-right: 5px;
  background-color: #eee;
  padding: 4px 8px;
  border-radius: 4px;
}

.komentari {
  list-style-type: none;
  margin: 10px 0;
  padding: 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 1rem;
}

.komentar {
  margin-bottom: 5px;
}
.dodaj-komentar {
  display: block;
  margin-top: 10px;
  padding: 8px 16px;
  background-color: #337ab7;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
</style>
