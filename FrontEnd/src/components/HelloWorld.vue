<template>
  <div class="login">
    <div class="usrName">
      <label for="usrTxt">Email</label>
      <input class="usrTxt" v-model="email" type="text" />
    </div>
    <div class="password">
      <label for="pswTxt">Password</label>
      <input class="pswTxt" v-model="password" type="password" />
    </div>
    <div class="lBtn">
      <button @click="proveriPodatke">Login</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "HelloWorld",
  props: {
    msg: String,
  },
  data() {
    return {
      email: "",
      password: "",
    };
  },
  methods: {
    proveriPodatke() {
      axios
        .post("/demo/api/korisnici/login", {
          email: this.email,
          password: this.password,
        })
        .then((response) => {
          localStorage.setItem("isLoggedIn", true);
          localStorage.setItem("jwt", response.data.jwt);
          localStorage.setItem("imeprezime", response.data.imeprezime);
          localStorage.setItem("tip", response.data.role);
          if (response.data.role === "Admin") {
            localStorage.setItem("isAdmin", true);
          } else {
            localStorage.setItem("isAdmin", false);
          }
          window.location.reload();
        })
        .catch(function (error) {
          console.log(error);
        });
      // then((response) => console.log(JSON.stringify(response)))
      // .catch((error) => {
      //   this.errorMessage = error.message;
      //   console.error("There was an error!", error);
      // });
    },

    //   methods: {
    //   proveriPodatke() {
    //     if (this.email === "mika" && this.password === "mika1") {
    //       console.log("Uspesno logovan");
    //       this.$router.push("/main");
    //     } else {
    //       console.log("Los input");
    //     }
    //   },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.login {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 1rem;
  padding: 1rem;
}

.login .usrName {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
}

.login .password {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
}
.login .lBtn {
  width: 5%;
}
.login .lBtn button {
  width: 100%;
}
</style>
