<template>
  <div class="admin-layout">
    <!-- 顶部导航栏 -->
    <header class="navbar">
      <div class="logo">实习直聘管理系统</div>
      <div class="breadcrumb">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item :to="{ path: '/admin' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>{{ currentMenu }}</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <div class="admin-info">
        <el-dropdown>
          <span class="el-dropdown-link">
            管理员 <i class="el-icon-arrow-down"></i>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item>个人信息</el-dropdown-item>
              <el-dropdown-item>退出登录</el-dropdown-item>
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
        <el-menu-item index="/admin">
          <i class="el-icon-s-home"></i>
          <span>首页</span>
        </el-menu-item>
        <el-menu-item index="/admin/users">
          <i class="el-icon-user"></i>
          <span>用户管理</span>
        </el-menu-item>
        <el-menu-item index="/admin/roles">
          <i class="el-icon-s-custom"></i>
          <span>角色管理</span>
        </el-menu-item>
        <!-- 其他菜单项... -->
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
import { useRoute, useRouter } from 'vue-router'

export default {
  name: 'AdminLayout',
  setup() {
    const route = useRoute()
    const router = useRouter()

    const activeMenu = computed(() => route.path)
    const currentMenu = computed(() => route.meta.title || '')

    const handleSelect = (key) => {
      router.push(key)
    }

    return {
      activeMenu,
      currentMenu,
      handleSelect
    }
  }
}
</script>

<style lang="scss" scoped>
.admin-layout {
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