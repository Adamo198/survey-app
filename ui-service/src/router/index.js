import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AnswerSurvey from "@/components/AnswerSurvey";
import AnswerList from "@/components/AnswerList";

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/survey/:id?',
    name: 'survey',
    component: AnswerSurvey
  },
  {
    path: '/answer',
    name: 'answer',
    component: AnswerList
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
