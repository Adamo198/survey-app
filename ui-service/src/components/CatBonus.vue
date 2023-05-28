<template>
  <div>
    <h1>Random Cat Image To Make Your Day Better</h1>
    <div v-if="loading">Loading...</div>
    <div v-else>
      <div style="margin: 16px; padding-left: 30% ; padding-right: 30%">
        <van-button round block @click="getRandomCatImage" color="green" type="primary">Get Another Cat
        </van-button>
      </div>
      <van-form @submit="onSubmit">
      <div style="margin: 16px; padding-left: 30% ; padding-right: 30%">
        <van-button round block type="primary" native-type="submit">
          Save This Beautiful Image
        </van-button>
      </div>
      </van-form>
      <img :src="catImageUrl" alt="Random Cat" class="cat-image" />
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "SaveImages",
  data() {
    return {
      id: '',
      catImageUrl: '',
      loading: false
    };
  },
  mounted() {
    this.getRandomCatImage();
  },
  methods: {
    getRandomCatImage() {
      this.loading = true;
      axios
        .get('https://api.thecatapi.com/v1/images/search')
        .then(response => {
          this.id = response.data[0].id;
          this.catImageUrl = response.data[0].url;
          this.loading = false;
        })
        .catch(error => {
          console.error(error);
          this.loading = false;
        });
    },
    async onSubmit(e) {
      console.log(e)
      // e.preventDefault()
      const submitReq = {
        id: this.id,
        catImageUrl: this.catImageUrl,
      }
      console.log(submitReq)
      const res = await fetch('api/images',
          {
            method: 'POST',
            headers: {
              'Content-type': 'application/json',
            },
            body: JSON.stringify(submitReq),
          })
      if (res.status >= 200 && res.status <300) {
        this.id = "";
        this.catImageUrl = "";
        let responseId = await res.json();
        console.log(res)
        console.log(responseId.id)
        alert("Image saved")
        this.getRandomCatImage();
      } else {
        alert("Error during send, try again!")
      }
    }
  }
};
</script>


<style>
.cat-image {
  max-width: 70%;
  max-height: 70%;
  width: auto;
  height: auto;
}
</style>