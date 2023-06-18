<template>
  <div class="main">
    <h1>Wyniki ankiety {{this.surveyId}}</h1>
    <!-- <h1>{{this.surveyId}}</h1>
    <div v-if="this.surveyId === undefined">
      <n-input-group >
        <n-input v-model:value="id" type="text" placeholder="Find Answers By Survey ID"/>
        <n-button size="small" color="green" @click="getAnswersBySurveyId(id)">Find</n-button>
      </n-input-group>
    </div> -->
    <n-collapse>
      <n-collapse-item v-for="(qa, index) in answers" :key="index" :title="qa.id" :name="index" class="answer">
        <h4>Imię: {{qa.name}}</h4>
        <h4>Nazwisko: {{qa.lastName}}</h4>
        <h4>Email: {{qa.email}}</h4>
        <div v-for="(ae, aeId) in qa.entries" :key="aeId">
          <h4>{{aeId + 1}}. Question :  {{ae.question.name}}</h4>
          <h5>Odpowiedź:  {{ae.answer}}</h5>
        </div>
      </n-collapse-item>
    </n-collapse>
  </div>
</template>

<script>


// import {NButton, NCollapse, NCollapseItem, NInput, NInputGroup} from "naive-ui";
import {NCollapse, NCollapseItem} from "naive-ui";
import { getAuthorization } from '@/services/auth-header';

export default {
  name: "AnswerList",
  components: {NCollapse, NCollapseItem},
  data() {
    return {
      id: "",
      answers: "",
      surveyId: ""
    }
  },
  methods: {
    async getAnswersBySurveyId(id) {
      const res = await fetch(
      "/api/answer/search/findAllBySurveyId?surveyId=" + id,
{method: "GET", 
headers: {
              'Authorization': getAuthorization()
            }
            })
      if (res.status === 200) {
        const response =  await res.json()
        console.log(response)
        console.log(response._embedded)
        this.answers = response._embedded.answer
      }
    },
    pretty(value) {
      return JSON.stringify(JSON.parse(value), null, 2);
    }
  },
  async created() {
    if (this.$route.params.id !== '') {
      this.surveyId = this.$route.params.id
      await this.getAnswersBySurveyId(this.surveyId);
    }
  }
}
</script>

<style scoped>
.answer {
  text-align: left;
}
.main {
  margin-right: 10%;
  margin-left: 10%;
}
</style>
