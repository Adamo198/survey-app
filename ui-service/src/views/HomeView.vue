<template>
  <div class="home">
    <CreateSurvey/>
  </div>
</template>

<script>
// @ is an alias to /src


import CreateSurvey from "@/components/CreateSurvey";

export default {
  name: 'HomeView',
  components: {
    CreateSurvey
  }
}
</script>
