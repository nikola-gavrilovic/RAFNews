<template>
  <div>
    <h2>Vesti sa tagom: {{ tagId }}</h2>
    <ul>
      <li v-for="vest in filteredVesti" :key="vest.id">
        {{ vest.naslov }}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "VestiPoTagu",
  data() {
    return {
      vesti: [],
    };
  },
  computed: {
    filteredVesti() {
      return this.vesti.filter((vest) => {
        return vest.tagovi.some((tag) => tag.id.toString() === this.tagId);
      });
    },
  },
  created() {
    this.tagId = this.$route.params.tagId;
  },
};
</script>
