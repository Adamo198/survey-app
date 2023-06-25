<template>
  <div class="container">
    <header class="jumbotron">
      <h3>
        <strong>{{currentUser.username}}</strong>
      </h3>
    </header>
    <p>
      <strong>Id:</strong>
      {{currentUser.id}}
    </p>
    <p>
      <strong>Email:</strong>
      {{currentUser.email}}
    </p>
    <div class="">
    <p>Lista ankiet</p>
      <ul class="list-group">
        <li class="list-group-item" v-for="survey in surveys" :key="survey.id">
          <div class="container-sm">
            <div class="row row-cols-auto">
            <p class="col-auto me-auto">Id: {{ survey.id }}, Tytuł: {{ survey.title }}</p>
            <router-link type="button" class="btn btn-danger col-auto" :to="`/answer/${survey.id}`" >odpowiedzi</router-link>
            <button type="button" class="btn btn-danger col-auto" @click="removeSurvey(survey.id)">usuń</button>
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
import axios from 'axios';

export default {
  name: 'Profile',
  data() {
    return {
      surveys: []
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
      const res = await fetch('http://localhost:9090/api/survey/' + id,
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
      axios
      .get('http://localhost:9090/api/survey/search/findByOwner_Username?username=' + this.$store.state.auth.user.username, {
        headers: {
          'Authorization': getAuthorization()
        }
      })
      .then(response => {
        let resp = response.data;
        console.log(resp)
        console.log('response', response.data._embedded.survey)
        this.surveys = response.data._embedded.survey;
        console.log('this surveys');
        console.log(this.surveys);
      })
      .catch(err => {
        console.log('Could not load user surveys');
      });
      // const res = await fetch('api/survey/search/findByEmail?email=' + this.$store.state.auth.user.email,
      //     {
      //       method: 'GET',
      //       headers: {
      //         'Content-type': 'application/json',
      //         'Authorization': getAuthorization()
      //       }
      //     })
      // if (res.status >= 200 && res.status <300) {
      //   let response = await res.json();
      //   console.log(res)
      //   console.log('response', response._embedded.survey)
      //   this.surveys = response._embedded.survey;
      //   console.log('this surveys');
      //   console.log(this.surveys);
      // } else {
      //   console.log('Could not load user surveys');
      // }
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
