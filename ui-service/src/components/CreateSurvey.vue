<template>
  <van-row justify="center">
    <van-col span="10">
      <van-form @submit="onSubmit">
        <div class="cs-block">
          <van-cell-group inset>
            <van-field
                v-model="email"
                name="email"
                label="Email"
                placeholder="Email"
                :rules="[{ required: true, message: 'Email is required' }]"
            />
            <van-field
                v-model="title"
                type="title"
                name="title"
                label="Title"
                placeholder="Survey Title"
                :rules="[{ required: true, message: 'Title is required' }]"
            />
          </van-cell-group>
        </div>

        <van-cell-group>
          <div v-for="(q, index) in questions" :key="index" class="cs-question">
            <div class="cs-block">
              <van-cell>
                <van-field v-model="q.name" label="Question" placeholder="Question" :rules="[{ required: true, message: 'Question is required' }]"/>
              </van-cell>

              <van-cell title="Question type">
                <van-radio-group v-model="q.type">
                  <van-radio name="text">Text</van-radio>
                  <van-radio name="radio">Radio</van-radio>
                </van-radio-group>
              </van-cell>
            </div>

            <div v-if="q.type === 'radio'" class="cs-option">
              <van-row class="option-one" justify="space-between" v-for="(opt, index) in q.options"
                       v-bind:key="index">
                <van-col>
                  <van-field v-model="opt.text" label="Option" placeholder="Option" :rules="[{ required: true, message: 'Option is required' }]"/>
                </van-col>
                <van-col class="center">
                  <van-button @click="deleteOption(q, index)" size="mini" icon-position="right"
                              icon="cross" color="red"
                              round></van-button>
                </van-col>
              </van-row>
              <van-col span="8" style="margin: 16px; padding-left: 50%">
                <van-button @click="addOption(q)" size="small" round icon="plus" color="green"
                            type="primary"></van-button>
              </van-col>
            </div>
          </div>


        </van-cell-group>

        <div style="margin: 16px; padding-left: 50%">
          <van-button @click="addQuestion" icon="plus" color="green" type="primary">Add Question
          </van-button>
        </div>
        <div style="margin: 16px; padding-left: 30% ; padding-right: 30%">
          <van-button round block type="primary" native-type="submit">
            Submit
          </van-button>
        </div>
      </van-form>
    </van-col>
  </van-row>
</template>

<script>


export default {
  name: "CreateSurvey",
  data() {
    return {
      questions: [],
      email: "",
      title: "",
    }
  },
  methods: {
    addQuestion() {
      this.questions.push({
        id: "",
        name: "",
        type: "",
        options: [{}]
      })
    },
    addOption(q) {
      q.options.push({
        id: "",
        text: ""
      })
    },
    deleteOption(q, index) {
      q.options.splice(index, 1);
    }
    ,
    async onSubmit(e) {
      console.log(e)
      // e.preventDefault()
      const surveyReq = {
        email: this.email,
        title: this.title,
        questions: this.questions
      }
      console.log(surveyReq)
      const res = await fetch('api/survey',
          {
            method: 'POST',
            headers: {
              'Content-type': 'application/json',
            },
            body: JSON.stringify(surveyReq),
          })
      if (res.status >= 200 && res.status <300) {
        this.email = "";
        this.title = "";
        this.questions = [];
        let responseId = await res.json();
        console.log(res)
        console.log(responseId.id)
        alert("Survey sent!" + responseId.id)
      } else {
        alert("Error during send")
      }
    }
  }
}
</script>

<style scoped>
.cs-block {
  margin: 10%;
  border: solid 1px dimgrey;
}

.cs-option {
  margin: 5%;
  border: solid 1px dimgrey;
}

.cs-question {
  margin: 10%;
  border: solid 1px aquamarine;
}

span {
  width: 30px;
  float: right;
  cursor: pointer;
}

span:hover {
  color: brown;
}

.option-one {

}

.center {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 2%;
}
</style>
