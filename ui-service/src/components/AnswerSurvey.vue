<template>
  <div class="main">
    <div v-if="$route.params.id === ''">
      <h1>Choose Survey And Send Your Answers</h1>
      <n-input-group>
        <n-input v-model:value="id" type="text" placeholder="Find Survey By ID"/>
        <n-button size="small" color="green" @click="findSurvey(id)">Find</n-button>
      </n-input-group>
    </div>

    <n-form
        ref="formRef"
        :label-width="80"
        :model="answer"
        :rules="rules"
        size="medium"
        class="form"
    >
      <h2>Title: {{ this.survey.title }}</h2>

      <n-form-item
          :span="12"
          :label="'Name:'"
          path="answer"
      >
        <n-input v-model:value="answer.name" type="text" placeholder="Name"/>
      </n-form-item>
      <n-form-item
          :span="12"
          :label="'Last Name:'"
          path="answer"
      >
        <n-input v-model:value="answer.lastName" type="text" placeholder="Last Name"/>
      </n-form-item>

      <!-- <n-form-item
          :span="12"
          :label="'Email:'"
          path="answer"
      >
        <n-input v-if="!currentUser" v-model:value="answer.email" type="text" placeholder="Email"/>
      </n-form-item> -->

      <div v-for="(q,index) in survey.questions" v-bind:key="index">
        <n-form-item
            :span="12"
            :label=q.name
            path="survey"
            v-if="q.type === 'radio'"
        >
          <n-radio-group v-model:value="q.answer" name="radiogroup2">
            <n-radio v-for="(o, id) in q.options" v-bind:key="id"
                     :value="o.text">
              {{ o.text }}
            </n-radio>
          </n-radio-group>
        </n-form-item>

        <n-form-item
            :span="12"
            :label=q.name
            path="survey"
            v-if="q.type === 'text'"
        >
          <n-input v-model:value="q.answer" type="text" placeholder="Answer"/>
        </n-form-item>
      </div>

      <div style="display: flex; justify-content: flex-end">
        <n-button round type="primary" color="green" @click="sendAnswer">
          Send Answer
        </n-button>
      </div>
    </n-form>

  </div>


</template>

<script>
import {NButton, NForm, NFormItem, NInput, NInputGroup, NRadio, NRadioGroup} from "naive-ui";
import { getAuthorization } from '@/services/auth-header';

export default {
  name: "AnswerSurvey",
  components: {NInput, NButton, NForm, NFormItem, NInputGroup, NRadioGroup, NRadio},
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
  },
  data() {
    return {
      id: "",
      survey: {},
      answer: {},
      rules: {
        user: {
          name: {
            required: true,
            message: "Please input your name",
            trigger: "blur"
          },
          age: {
            required: true,
            message: "Please input your age",
            trigger: ["input", "blur"]
          }
        },
        phone: {
          required: true,
          message: "Please input your number",
          trigger: ["input"]
        }
      }
    }
  },
  methods: {
    async findSurvey(id) {
      const res = await fetch("/api/survey/" + id)
      this.id = id
      if (res.status === 200) {
        this.survey = await res.json()
      }
    },
    async sendAnswer() {
      const req = {
        surveyId: this.id,
        name: this.answer.name,
        lastName: this.answer.lastName,
        email: this.currentUser ? this.currentUser.email : 'anonim',
        entries: this.prepareAnswerEntry()
      }
      console.log(req)
      const res = await fetch("/api/answer", {
        method: 'POST',
        headers: {
          'Content-type': 'application/json',
          'Authorization': getAuthorization()
        },
        body: JSON.stringify(req),
      })
      if (res.status >= 200 && res.status <300) {
        alert("Answer Sent")
      } else {
        alert("Answer Error!")
      }
      this.id = ""
      this.survey = {}
      this.answer.name = ""
      this.answer.lastName = ''
      this.answer.email = ''
      this.answer = {}
    },
    prepareAnswerEntry() {
      const entries = this.survey.questions.map(value => {
        return {
          question: {
            name: value.name,
            type: value.type,
            options: value.options
          },
          answer: value.answer
        }
      })
      console.log(entries)
      return entries
    }
  },
  async created() {
    if (this.$route.params.id !== '') {
      await this.findSurvey(this.$route.params.id);
    }
  },

}
</script>

<style scoped>
.main {
  margin-right: 10%;
  margin-left: 10%;
}

.form {
  text-align: left;
}
</style>
