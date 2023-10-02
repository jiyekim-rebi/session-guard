import {defineStore} from "pinia";
import {ref} from "vue";

const isLogin = ref(false);

function login(userId, password) {
  console.log(userId)
}

export const useLoginStore = defineStore('login', () => {
  return {
    isLogin,
    login,
  }
})
