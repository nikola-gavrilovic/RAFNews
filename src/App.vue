<template>
  <div id="app">
    <ul v-if="isLoggedIn" class="nav">
      <li class="li_klasa" id="home_page">
        <router-link to="/main"> Home Page </router-link>
      </li>
      <li v-if="isLoggedIn" class="li_klasa" id="najcitanije">
        <router-link to="/najcitanije"> Najcitanije </router-link>
      </li>
      <li v-if="isAdmin" class="li_klasa" id="korisnici">
        <router-link to="/korisnici"> Korisnici </router-link>
      </li>
      <li v-if="isLoggedIn" class="li_klasa" id="vesti">
        <router-link to="/vesti"> Vesti </router-link>
      </li>
      <li v-if="isLoggedIn" class="li_klasa" id="vesti">
        <router-link to="/kategorije"> Kategorije </router-link>
      </li>
      <li class="li_klasa">
        <a @click="logout()"> {{ name }} Logout</a>
      </li>
    </ul>
    <!-- <img alt="Vue logo" src="./assets/logo.png"> -->
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "App",
  components: {},
  data() {
    return {
      isAdmin: localStorage.getItem("isAdmin") === "true",
      isLoggedIn: localStorage.getItem("isLoggedIn") === "true",
      name: localStorage.getItem("imeprezime"),
    };
  },
  methods: {
    logout() {
      localStorage.clear();
      this.$router.push("/login");
      window.location.reload();
    },
  },
  created() {
    if (this.isLoggedIn === false) {
      this.$router.push("/login");
    } else {
      this.$router.push("/main");
    }
  },
};
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: Arial, Helvetica, sans-serif;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
.nav {
  background-color: #2c3e50;
  width: 100%;
  height: 5rem;
  display: flex;
  justify-content: center;
  align-items: center;
}
ul {
  display: flex;
  gap: 1rem;
}
ul a {
  text-decoration: none;
  font-weight: 600;
  color: white;
}
.li_klasa :hover {
  color: antiquewhite;
}
.li_klasa {
  list-style: none;
  text-decoration: none;
}
</style>
