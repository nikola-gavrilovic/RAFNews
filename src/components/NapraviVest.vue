<template>
  <div class="container">
    <h2>Napravi novu vest</h2>
    <form @submit.prevent="napraviNovuVest">
      <label for="naslov">Naslov:</label>
      <input type="text" id="naslov" v-model="naslov" required />

      <label for="kategorija">Kategorija:</label>
      <select id="kategorija" v-model="izabranaKategorija" required>
        <option
          v-for="kategorija in kategorije"
          :key="kategorija.id"
          :value="kategorija"
        >
          {{ kategorija.ime }}
        </option>
      </select>

      <label for="tekst">Tekst:</label>
      <textarea id="tekst" v-model="tekst" required></textarea>

      <label for="tagovi">Tagovi:</label>
      <select id="tagovi" v-model="izabraniTag">
        <option v-for="tag in tagovi" :key="tag.id" :value="tag">
          {{ tag.tag }}
        </option>
      </select>
      <ul>
        <li v-for="tag in tagoviZaVest" :key="tag.id">{{ tag.tag }}</li>
      </ul>
      <input type="text" v-model="noviTag" placeholder="Unesite novi tag" />

      <div class="buttons">
        <button @click.prevent="dodajUListuTag">Dodaj Tag</button>

        <button type="submit">Napravi vest</button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      naslov: "",
      autor: localStorage.getItem("imeprezime"),
      izabranaKategorija: null,
      tekst: "",
      izabraniTag: null,
      noviTag: "",
      kategorije: [],
      tagovi: [],
      tagoviZaVest: [],
      korisnik: localStorage.getItem("korisnik"),
    };
  },
  created() {
    this.ucitajKategorije();
    this.ucitajTagove();
  },
  methods: {
    ucitajKategorije() {
      this.$axios
        .get("/demo/api/kategorije")
        .then((response) => {
          this.kategorije = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    ucitajTagove() {
      this.$axios
        .get("/demo/api/tagovi")
        .then((response) => {
          this.tagovi = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    napraviNovuVest() {
      if (!this.naslov || !this.izabranaKategorija || !this.tekst) {
        console.log("Molimo popunite sva polja.");
        return;
      }

      const noviTagovi = this.tagoviZaVest.map((tag) => tag.tag);

      const novaVest = {
        naslov: this.naslov,
        autor: this.autor,
        tekst: this.tekst,
        kategorija: this.izabranaKategorija,
        tagovi: noviTagovi,
      };

      this.$axios
        .post("/demo/api/vesti", novaVest)
        .then((response) => {
          console.log("Vest uspešno kreirana:", response.data);
        })
        .catch((error) => {
          console.log("Greška prilikom kreiranja vesti:", error);
        });
    },
    dodajUListuTag() {
      if (this.izabraniTag) {
        this.tagoviZaVest.push(this.izabraniTag);
        this.izabraniTag = null;
      }
    },
    dodajTag(noviTag) {
      // Dodavanje novog taga u bazu preko API-a
      // Dodaj novi tag u niz dodanih tagova
      this.tagovi.push(noviTag);

      // Resetuj noviTag
      this.noviTag = "";
    },
  },
};
</script>

<style>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  gap: 1rem;
}

form {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  width: 50%;
  margin-top: 20px;
  gap: 1rem;
}

form label {
  display: block;
  margin-bottom: 8px;
}

form input[type="text"],
form textarea {
  width: 100%;
  padding: 8px;
  margin-bottom: 16px;
}

form select {
  width: 100%;
  padding: 8px;
  margin-bottom: 16px;
}
.buttons {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  gap: 1rem;
}
form button {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 8px 16px;
  background-color: #337ab7;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
</style>
