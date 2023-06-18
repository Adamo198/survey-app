<template>
  <div class="container">
    <header class="jumbotron">
      <h3>
        <strong>{{currentUser.username}}</strong> Profile
      </h3>
    </header>
    <p>
      <strong>Token:</strong>
      {{currentUser.accessToken.substring(0, 20)}} ... {{currentUser.accessToken.substr(currentUser.accessToken.length - 20)}}
    </p>
    <p>
      <strong>Id:</strong>
      {{currentUser.id}}
    </p>
    <p>
      <strong>Email:</strong>
      {{currentUser.email}}
    </p>
    <strong>Authorities:</strong>
    <ul>
      <li v-for="role in currentUser.roles" :key="role">{{role}}</li>
    </ul>
    <div class="">
    <p>Ankiety</p>
      <ul class="list-group">
        <li class="list-group-item" v-for="survey in surveys" :key="survey.id">
          <div class="container-sm">
            <div class="row row-cols-auto">
            <p class="col-auto me-auto">{{ survey.title }}</p>
            <button type="button" class="btn btn-danger col-auto" @click="removeSurvey(survey.id)">remove</button>
            <!-- <van-button block size="mini" @click="removeSurvey" icon="cross" color="red" type="primary">
            </van-button> -->
            </div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { getAuthorization } from '@/services/auth-header';
export default {
  name: 'Profile',
  data() {
    return {
      surveys: [
        {id: 1, email: "jacek@jacek.com", title: "Ankieta o pogodzie"},
        {id: 2, email: "jacek@jacek.com", title: "Ankieta 2"}
      ]
    }
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  },
  methods: {
    async removeSurvey(id) {
      console.log(id)
      // e.preventDefault()
      const deleteReq = {
        id: id
      }
      // console.log(deleteReq)
      const res = await fetch('api/survey/' + id,
          {
            method: 'DELETE',
            headers: {
              'Content-type': 'application/json',
              'Authorization': getAuthorization()
            },
            body: JSON.stringify(deleteReq),
          })
      if (res.status >= 200 && res.status <300) {
        this.email = "";
        this.title = "";
        this.questions = [];
        let responseId = await res.json();
        console.log(res)
        // console.log(responseId.id)
        alert("Survey deleted!")
    this.loadSurveys();
      } else {
        alert("Error during send, try again!")
      }
    },
    async loadSurveys() {
      // e.preventDefault()
      const deleteReq = {
        email: this.$store.state.auth.user.email
      }
      // console.log(deleteReq)
      const res = await fetch('api/survey?email=' + this.$store.state.auth.user.email,
          {
            method: 'GET',
            headers: {
              'Content-type': 'application/json',
              'Authorization': getAuthorization()
            }
          })
      if (res.status >= 200 && res.status <300) {
        //this.email = "";
        //this.title = "";
        //this.questions = [];
        let response = await res.json();
        console.log(res)
        console.log('response', response._embedded.survey)
        this.surveys = response._embedded.survey;
        // console.log(responseId.id)
//         alert("Survey deleted!")
console.log('this surveys');
console.log(this.surveys);
      } else {
      console.log('Could not load user surveys');
//        alert("Error during send, try again!")
      }
    }
  },
  created() {
    this.loadSurveys();
  }
};
</script>
<style scoped>

@import'~bootstrap/dist/css/bootstrap.css'
</style>
