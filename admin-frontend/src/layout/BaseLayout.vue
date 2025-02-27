<template>
  <div class="app-wrapper">
    <!-- 顶部导航栏 -->
    <header class="navbar">
      <div class="logo">实习直聘管理系统</div>
      <div class="user-info">
        <el-dropdown>
          <span class="el-dropdown-link">
            {{ userInfo.name }}<i class="el-icon-arrow-down"></i>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </header>

    <!-- 左侧菜单 -->
    <aside class="sidebar">
      <el-menu
        :default-active="activeMenu"
        class="el-menu-vertical"
        @select="handleSelect"
      >
        <el-menu-item index="dashboard">
          <i class="el-icon-s-home"></i>
          <span>首页</span>
        </el-menu-item>
        <!-- 其他菜单项 -->
      </el-menu>
    </aside>

    <!-- 主内容区 -->
    <main class="main-content">
      <router-view></router-view>
    </main>
  </div>
</template>

<script>
import { ref, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'BaseLayout',
  setup() {
    const store = useStore()
    const router = useRouter()

    const userInfo = computed(() => store.state.user.userInfo)
    const activeMenu = ref('dashboard')

    const handleSelect = (key) => {
      router.push(`/${key}`)
    }

    const logout = async () => {
      await store.dispatch('user/logout')
      router.push('/login')
    }

    return {
      userInfo,
      activeMenu,
      handleSelect,
      logout
    }
  }
}
</script>

<style lang="scss" scoped>
.app-wrapper {
  min-height: 100vh;
  display: grid;
  grid-template-columns: 256px 1fr;
  grid-template-rows: 60px 1fr;
}

.navbar {
  grid-column: 1 / -1;
  background: white;
  border-bottom: 1px solid #dcdfe6;
  display: flex;
  align-items: center;
  padding: 0 20px;
  justify-content: space-between;
}

.sidebar {
  background: white;
  border-right: 1px solid #dcdfe6;
}

.main-content {
  padding: 20px;
  background: #f0f2f5;
}
</style> 