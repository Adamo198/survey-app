<template>
  <div class="main">
    <h1>{{$route.query.surveyId}}</h1>
    <div v-if="$route.query.surveyId === undefined">
      <n-input-group >
        <n-input v-model:value="id" type="text" placeholder="Find Answers By Survey ID"/>
        <n-button size="small" color="green" @click="getAnswersBySurveyId(id)">Find</n-button>
      </n-input-group>
    </div>
    <n-collapse>
      <n-collapse-item v-for="(qa, index) in answers" :key="index" :title="qa.id" :name="index" class="answer">
        <h4>Name: {{qa.name}}</h4>
        <h4>Last Name: {{qa.lastName}}</h4>
        <h4>Email: {{qa.email}}</h4>
        <div v-for="(ae, aeId) in qa.entries" :key="aeId">
          <h4>{{aeId + 1}}. Question :  {{ae.question.name}}</h4>
          <h5>Answer:  {{ae.answer}}</h5>
        </div>
      </n-collapse-item>
    </n-collapse>
  </div>
</template>

<script>


import {NButton, NCollapse, NCollapseItem, NInput, NInputGroup} from "naive-ui";

export default {
  name: "AnswerList",
  components: {NCollapse, NCollapseItem, NInputGroup, NInput, NButton},
  data() {
    return {
      id: "",
      answers: "",
      surveyId: ""
    }
  },
  methods: {
    async getAnswersBySurveyId(id) {
      const res = await fetch("/api/answer/search/findAllBySurveyId?surveyId=" + id)
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
    if (this.$route.query.surveyId !== '' && this.$route.query.surveyId !== undefined) {
      this.surveyId = this.$route.query.surveyId
      await this.getAnswersBySurveyId(this.$route.query.surveyId);
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
