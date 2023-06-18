import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import CreateSurvey from "@/components/CreateSurvey";
import AnswerSurvey from "@/components/AnswerSurvey";
import AnswerList from "@/components/AnswerList";
import CatBonus from "@/components/CatBonus";


import Home from "@/components/Home.vue";
import Login from "@/components/Login.vue";
import Register from "@/components/Register.vue";
// lazy-loaded
const Profile = () => import("@/components/Profile.vue")
const BoardAdmin = () => import("@/components/BoardAdmin.vue")
const BoardModerator = () => import("@/components/BoardModerator.vue")
const BoardUser = () => import("@/components/BoardUser.vue")

const routes = [
  {
    path: '/',
    name: 'default_path',
    component: Login
  },
  {
    path: '/create_survey',
    name: 'create_survey',
    component: CreateSurvey
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
  },
  {
    path: '/catbonus',
    name: 'catbonus',
    component: CatBonus
  },
  {
    path: "/login",
    component: Login,
  },
  {
    path: "/register",
    component: Register,
  },
  {
    path: "/profile",
    name: "profile",
    // lazy-loaded
    component: Profile,
  },
  {
    path: "/admin",
    name: "admin",
    // lazy-loaded
    component: BoardAdmin,
  },
  {
    path: "/mod",
    name: "moderator",
    // lazy-loaded
    component: BoardModerator,
  },
  {
    path: "/user",
    name: "user",
    // lazy-loaded
    component: BoardUser,
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})
router.beforeEach((to, from, next) => {
  const publicPages = ['/login', '/register', '/home', '/survey'];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem('user');

  // trying to access a restricted page + not logged in
  // redirect to login page
  if (authRequired && !loggedIn) {
    next('/login');
  } else {
    next();
  }
});

export default router
