<template>
<!--  <nav>-->
    <div class="nav">
      <router-link to="/">Create Survey</router-link> |
      <router-link to="/survey">Answer</router-link> |
      <router-link to="/answer">Results</router-link> |
      <router-link to="/catbonus">Make Yourself Smile!</router-link>
    </div>
    <router-view />
<!--  </nav>-->
</template>

<script>

export default {
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.nav {
  padding: 30px;
}

.nav a {
  font-weight: bold;
  color: #2c3e50;
}

.nav a.router-link-exact-active {
  color: #42b983;
}
</style>
