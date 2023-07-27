import Vue from "vue";
import "./plugins/axios";
import App from "./App.vue";
import Main from "./components/Main.vue";
import HelloWorld from "./components/HelloWorld.vue";
import VueRouter from "vue-router";
import Najcitanije from "./components/Najcitanije.vue";
import KorisnikView from "./components/KorisnikView.vue";
import VestiView from "./components/VestiView.vue";
import TagZaVesti from "./components/TagView.vue";
import NapraviKategoriju from "./components/NapraviKategoriju.vue";
import KategorijeView from "./components/KategorijeView.vue";
import NapraviVest from "./components/NapraviVest.vue";
import OtvoriVest from "./components/OtvoriVest.vue";
import EditKorisnika from "./components/EditKorisnika.vue";
import Axios from "axios";

const routes = [
  {
    path: "/main",
    component: Main,
  },
  {
    path: "/login",
    component: HelloWorld,
  },
  {
    path: "/najcitanije",
    component: Najcitanije,
  },
  {
    path: "/korisnici",
    component: KorisnikView,
  },
  {
    path: "/vesti",
    component: VestiView,
  },
  {
    path: "/tagovi/:id",
    component: TagZaVesti,
  },
  {
    path: "/napraviKategoriju",
    component: NapraviKategoriju,
  },
  {
    path: "/kategorije",
    component: KategorijeView,
  },
  {
    path: "/napraviVest",
    component: NapraviVest,
  },
  {
    path: "/otvoriVest/:id",
    component: OtvoriVest,
    props: true,
  },
  {
    path: "/editKorisnika",
    component: EditKorisnika,
    props: true,
  },
];

var router = new VueRouter({ routes });

Vue.config.productionTip = false;

Vue.use(VueRouter);

new Vue({
  Axios,
  router,
  render: (h) => h(App),
}).$mount("#app");
