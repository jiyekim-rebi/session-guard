import {defineStore} from "pinia";
import {ref} from "vue";
import axios from "axios";

const isLogin = ref(false);

function login(userId, password) {
  console.log(userId)

  const device = navigator.userAgent

  const config = {
    'Content-Type': 'application/json',
    'User-Agent': device,
  }

  // base url로 설정
  axios.post("http://localhost:8080/user/login", config)
    .then(function (response) {
      // 성공
      console.log(response);
    })
    .catch(function (error) {
      // 에러
      console.log(error);
    })

}

export const useLoginStore = defineStore('login', () => {
  return {
    isLogin,
    login,
  }
})
