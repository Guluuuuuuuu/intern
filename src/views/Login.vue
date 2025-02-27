<template>
  <div class="login-container">
    <el-card class="login-card">
      <h2 class="title">实习管理系统</h2>
      <el-form :model="loginForm" @submit.prevent="handleLogin">
        <el-form-item>
          <el-input 
            v-model="loginForm.username" 
            placeholder="用户名"
            prefix-icon="User">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-input 
            v-model="loginForm.password" 
            type="password" 
            placeholder="密码"
            prefix-icon="Lock">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-select 
            v-model="loginForm.role" 
            placeholder="请选择角色"
            style="width: 100%">
            <el-option label="管理员" value="admin"></el-option>
            <el-option label="学生" value="student"></el-option>
            <el-option label="企业" value="company"></el-option>
            <el-option label="学校" value="school"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="width: 100%" @click="handleLogin">
            登录
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const router = useRouter()
const loginForm = ref({
  username: '',
  password: '',
  role: ''
})

const handleLogin = async () => {
  try {
    const response = await axios.post('http://localhost:8888/api/login', loginForm.value)
    console.log('登录成功:', response.data)
    // TODO: 处理登录成功后的逻辑
  } catch (error) {
    console.error('登录失败:', error)
  }
}
</script>

<style scoped>
.login-container {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f5f5f5;
}

.login-card {
  width: 400px;
}

.title {
  text-align: center;
  margin-bottom: 30px;
  color: #409EFF;
}
</style> 