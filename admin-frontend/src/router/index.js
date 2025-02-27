import { createRouter, createWebHistory } from 'vue-router'
import AdminLayout from '@/layout/AdminLayout.vue'
import CompanyLayout from '@/layout/CompanyLayout.vue'
import SchoolLayout from '@/layout/SchoolLayout.vue'
import StudentLayout from '@/layout/StudentLayout.vue'

const routes = [
  {
    path: '/admin',
    component: AdminLayout,
    children: [
      {
        path: '',
        name: 'AdminDashboard',
        component: () => import('@/views/admin/dashboard/index.vue'),
        meta: { title: '首页' }
      },
      {
        path: 'users',
        name: 'Users',
        component: () => import('@/views/users/index.vue'),
        meta: { title: '用户管理' }
      },
      // 其他路由...
    ]
  },
  {
    path: '/company',
    component: CompanyLayout,
    children: [
      {
        path: '',
        name: 'CompanyDashboard',
        component: () => import('@/views/company/dashboard/index.vue'),
        meta: { title: '企业中心' }
      }
    ]
  },
  {
    path: '/school',
    component: SchoolLayout,
    children: [
      {
        path: '',
        name: 'SchoolDashboard',
        component: () => import('@/views/school/dashboard/index.vue'),
        meta: { title: '学校中心' }
      }
    ]
  },
  {
    path: '/student',
    component: StudentLayout,
    children: [
      {
        path: '',
        name: 'StudentDashboard',
        component: () => import('@/views/student/dashboard/index.vue'),
        meta: { title: '学生中心' }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 添加路由守卫
router.beforeEach((to, from, next) => {
  // 检查用户是否已登录
  const user = localStorage.getItem('user')
  
  if (!user && to.path !== '/login') {
    next('/login')
  } else {
    next()
  }
})

export default router 