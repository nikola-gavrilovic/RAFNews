<template>
  <div class="home_page">
    <ul>
      <li v-for="vest in vesti" :key="vest.id">
        <div class="vest">
          <div class="header_vest">
            <router-link :to="`/otvoriVest/${vest.id}`" class="vest_dugme">
              <a href="#" @click="getVestById(vest.id)">
                {{ vest.naslov }}
              </a></router-link
            >
            <div id="broj_pregleda">Broj poseta: {{ vest.brojPoseta }}</div>
          </div>
          <div id="kategorija_vest">{{ vest.kategorija }}</div>
          <div class="autor_datum">
            <div id="autor_vest">{{ vest.autor }}</div>
            <div id="datum_kreiranja_vest">{{ vest.vremeKreiranja }}</div>
          </div>
          <div id="tekst_vest">
            <p>{{ vest.tekst }}</p>
          </div>
          <div id="tagovi_vest">
            <div v-for="tag in vest.tagovi" :key="tag.id">
              <router-link :to="`/tagovi/${tag.id}`">
                <a href="#" @click="getTagById(tag.id)">
                  {{ tag.tag }}
                </a>
              </router-link>
            </div>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "NajcitanijiComponent",
  props: {
    msg: String,
  },
  data() {
    return {
      vesti: [],
    };
  },
  methods: {
    getTagById(id) {
      this.vesti = null;
      this.$axios.get("/demo/api/tagovi/" + id + "/vesti").then((response) => {
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
    this.$axios.get("/demo/api/vesti/najcitanije").then((response) => {
      this.vesti = response.data;
    });
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.home_page {
  font-family: Arial, sans-serif;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-top: 1rem;
}

.home_page ul {
  display: flex;
  flex-direction: column;
  width: 40%;
  gap: 1rem;
}
.home_page li {
  height: 40%;
}
.vest {
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
}

.header_vest {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

#naslov_vest a {
  font-weight: bold;
  text-decoration: none;
  color: #333;
}

#broj_pregleda {
  font-size: 12px;
  color: #777;
}

#kategorija_vest {
  font-size: 14px;
  margin-bottom: 5px;
}

.autor_datum {
  display: flex;
  justify-content: space-between;
  font-size: 12px;
  color: #777;
  margin-bottom: 10px;
}

#tagovi_vest {
  display: flex;
  flex-wrap: wrap;
  margin-top: 10px;
}

#tagovi_vest > div {
  margin-right: 5px;
  margin-bottom: 5px;
}

#tagovi_vest a {
  font-size: 12px;
  text-decoration: none;
  color: #555;
  padding: 2px 5px;
  border: 1px solid #ccc;
  border-radius: 2px;
}

#tagovi_vest a:hover {
  background-color: #f2f2f2;
}

#tekst_vest {
  margin-bottom: 10px;
}
.vest_dugme {
  text-decoration: none;
  color: black;
}
.vest_dugme:hover {
  color: gray;
}
</style>
