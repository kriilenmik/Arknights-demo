import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import VideoPage from '@/views/index/indexVideo'

Vue.use(VueRouter)
const routes = [

    //  ========== 默认路由重定向 =============
  // {
  //   path: '/',
  //   name: 'HomeIndex',
  //   redirect: '/index1/video',
  // },


    // ============= 忘记密码页面跳转 ===================
  {
    path: '/user/verify',
    name: 'User Forget Pass',
    component:() => import('../views/user/user-forget'),
  },
  {
    path: '/simulator',
    name: 'User Forget Pass',
    component:() => import('../views/user/simulator')
  },


    // ============= 页面index子路由跳转 =================
  {
    path: '/index1',
    name: 'index1-1',
    component: () => import('../views/index/index'),
    children: [
      {
        path: '',
        component: () => import('../views/index/index'),
      },
      {
        path: 'cc',
        component: () => import('../views/index/exp-c')
      },
      {
        path: 'zz',
        component: () => import('../views/index/exp-z')
      },
      {
        path: 'video',
        name: 'videoPage',
        component: () => import('../views/index/indexVideo')
      },
    ]

  },
  // ============= user登录菜单信息页 ==================
  {
    path: '/user',
    name: 'User Form Page',
    component: () => import('../views/user/user-form'),
    children: [
      {
        path: 'home',
        name: 'userHome',
        component:() => import('../views/user/form')
      },
      {
        path: 'resetPwd',
        name: 'resetPwd',
        component:() => import('../views/user/user-forget')
      },
      {
        path: 'deleteUser',
        name: 'deleteUser',
        component:() => import('../views/user/user-delete')
      },
    ]
  },

// ================== 管理员页面 =======================
{
  path: '/manager/log',
  name: 'manager_login',
  component:() => import('../views/manager/managerLogin')
},

{
  path: '/manager',
  name: 'manager',
  component:() => import('../views/manager/manage'),
  children: [
    {
      path: 'home',
      name: '主页',
      component:() => import('../views/manager/managerHome')
    },
    {
      path: 'user',
      name: '用户管理',
      component:() => import('../views/manager/managerUser')
    },
    {
      path: 'information',
      name: '公告管理',
      component:() => import('../views/manager/managerInformation')
    },
    {
      path: 'picture',
      name: '走马灯图',
      component:() => import('../views/manager/managerPicture')
    },
  ]
},


// ====== 通配符路由（*）来捕获所有不匹配的路径，放置末尾以防覆盖 =======
  {
    path: '*',
    name: 'HOMEPAGE',
    redirect: '/index1/video',
  }
]



// 路由守卫
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) =>{


  // ======== 视频背景预加载 =============
  // if (to.name === 'VideoPage'){
  //   const video = document.createElement('video');
  //   video.src = '../../assets/miku1.mp4';
  //   video.preload = 'auto';
  //   // 监听视频加载完成事件
  //   video.addEventListener('loadeddata', () => {
  //     console.log('Video preloaded successfully!');
  //     // 视频加载完成后继续路由跳转
  //     next();
  //   });
  // } else {
  //   next();
  // }

  localStorage.setItem("currentPathName", to.name)  //设置当前路由名称，为了在Header组件中使用
  next()              //放行路由
})

export default router
