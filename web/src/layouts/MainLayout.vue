<template>
  <q-layout view="hHh lpr lFr">

    <q-header elevated class="bg-primary text-white" height-hint="120">
      <q-toolbar>
        <q-toolbar-title style="padding: 15px;">
          <q-avatar>
            <img src="https://cdn.quasar.dev/logo-v2/svg/logo-mono-white.svg">
          </q-avatar>
          Session-Guard
        </q-toolbar-title>
      </q-toolbar>

      <!--      <q-tabs align="left">-->
      <!--        <q-route-tab to="/page1" label="Page One" />-->
      <!--        <q-route-tab to="/page2" label="Page Two" />-->
      <!--        <q-route-tab to="/page3" label="Page Three" />-->
      <!--      </q-tabs>-->
    </q-header>

    <q-page-container
      v-if="!isLogin"
      class="fit column wrap justify-center items-center content-center">
        <div>
          <q-card class="q-pa-md align-center" style="min-width: 400px; max-width: 400px;">
            <q-card-section>
              <h3 class="text-h5">Todo-List 로그인</h3>
            </q-card-section>
            <q-card-section>
              <q-input v-model="userId" label="이메일"/>
              <q-input v-model="password" label="비밀번호" type="password"/>
            </q-card-section>
            <q-card-actions>
              <q-btn color="primary" @click="requestLogin">로그인</q-btn>
            </q-card-actions>
          </q-card>
        </div>
      <div style="overflow: auto; height: 150px;"></div>
    </q-page-container>

    <q-page-container v-if="isLogin">
      <router-view/>
    </q-page-container>
  </q-layout>
</template>

<script setup>
import {useLoginStore} from "stores/login"
import {computed, ref} from "vue";

const login = useLoginStore()

const userId = ref('')
const password = ref('')
const isLogin = computed(() => {
  return login.isLogin
})

function requestLogin() {

  if (userId.value == '') {
    alert('ID를 입력해주세요')
    return
  }

  if (password.value == '') {
    alert('Password를 입력해주세요')
    return
  }

  login.login(userId.value, password.value)
}

</script>
