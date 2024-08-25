<template>
  <div class="class__container">

    <div class="aside">
      <div class="page__change__container">
        <div style="color: white; font-size: x-large">001//003</div>
      </div>
    </div>

    <!-- ==================== HEADER CONTAINER ======================== -->

    <div class="header__container">
    <header class="header__container">
      <nav class="nav container">
        <a class="logo">
          <router-link to="/index1/video" class="router__link__btn nav__link" style="font-size: 20px"><img :src="logo" alt="An ArkNights Pic"></router-link>
        </a>
        <div class="nav__menu" id="nav-menu" >
          <ul class="nav__list">

            <li class="nav__item">
                                              <!--  === exact -> 路径完全匹配启用变色color 根据网页路径高亮导航栏 === -->
                <router-link to="/index1/video" exact active-class="click__color" style="text-decoration: none" class="nav__link" >
                <div class="router__link__btn" style="font-size: 18px">INDEX</div>
                <div class="router__link__btn" style="font-size: 12px">首页</div>
                </router-link>
<!--                <router-link to="/index1/video" class="router__link__btn nav__link" style="font-size: 20px">INDEX</router-link>-->
            </li>

            <li class="nav__item">
              <router-link to="/index1/zz" exact active-class="click__color" style="text-decoration: none" class="nav__link">
                <div class="router__link__btn" style="font-size: 18px">INFORMATION</div>
                <div class="router__link__btn" style="font-size: 12px">情报</div>
              </router-link>
<!--                <router-link to="/index1/zz" class="router__link__btn nav__link" style="font-size: 20px">INFORMATION</router-link>-->
            </li>

            <li class="nav__item">
              <router-link to="/index1/cc" exact active-class="click__color" style="text-decoration: none" class="nav__link">
                <div class="router__link__btn" style="font-size: 18px">WORLD</div>
                <div class="router__link__btn" style="font-size: 12px">设定</div>
              </router-link>
<!--                <router-link to="/index1/video" class="router__link__btn nav__link" style="font-size: 20px">INDEX</router-link>-->
            </li>

          </ul>
        </div>

        <div class="nav__container">
        <div class="nav__actions">

          <!-- =========== LOGIN_User_Button ========== -->
          <i class="el-icon-user-solid nav__login" id="login-btn" @click="isLoginClick"></i>

        </div>
        </div>

      </nav>

    </header>
    </div>

    <!-- ========================== LOGGING FORM ====================== -->

    <div class="login" id="login">
      <form  class="login__form" v-if="currentForm === 'login'">
        <h2 class="login__title">登 录</h2>
        <div class="login__group">
          <div>
            <input type="text" placeholder="请输入用户名" class="login__input" v-model="user.username">
          </div>
          <div>
            <input type="password" placeholder="请输入密码" id="password" class="login__input" v-model="user.password">
          </div>
          <p class="login__signup" style="cursor: default; margin-top: 10px">
            还没有账号？ <a @click="switchForm('register')" style="cursor: pointer">立即注册</a>
          </p>

<!--          <a href="/user/verify" target="_blank" style="text-decoration: none" class="login__forgot">-->
<!--            忘记密码-->
<!--          </a>-->

          <button type="submit" class="login__button" @click.prevent="Login" style="margin-top: 1em">登录</button>
        </div>
        <i class="el-icon-close login__close" id="login-close" @click="isLoginClose"></i>
      </form>

      <!-- ======= REGISTER FORM ========== -->
      <form v-if="currentForm === 'register'" class="login__form">
        <h2 class="login__title">注 册</h2>
        <div class="login__group">
          <div>
            <input type="text" placeholder="请输入用户名" class="login__input" v-model="user1.username">
          </div>
          <div>
            <input type="text" placeholder="请输入昵称" class="login__input" v-model="user1.nickname">
          </div>
          <div>
            <input type="text" placeholder="请输入绑定邮箱" class="login__input" v-model="user1.email">
          </div>
          <div>
            <input type="password" placeholder="请输入密码" class="login__input" v-model="user1.password">
          </div>
          <div>
            <input type="password" placeholder="请再次输入密码" class="login__input" v-model="user1.password1">
          </div>
          <p class="login__signup" style="cursor: default;margin-top: 10px">
            已有账号？ <a @click="switchForm('login')" style="cursor: pointer">返回登录</a>
          </p>
          <button type="submit" class="login__button" @click.prevent="Register" style="margin-top: 1em">注册</button>
        </div>
        <i class="el-icon-close login__close" @click="isLoginClose"></i>

      </form>
    </div>


      <!-- =========================== PAGE CHANGE ======================== -->

    <div class="change__container">
      <transition name="slide" >
        <router-view/>
      </transition>
    </div>


  </div>
</template>

<script>
export default {
  name: "index",

  data(){
    return{
      // A Hyperic Ark Icon
      logo: require('@/assets/10001.jpg'),

      // 用户提交登录表单数据
      user:{},

      // 注册表单
      user1:{},

      // 表单切换默认login页
      currentForm:'login',

      // password1判断一致
      password1: '',

      // 默认值
      nickname: '',
    };

  },

  methods: {

    /* ===================== SUBMIT LOGGING FORM ================= */
    Login(event){
      event.preventDefault();
      // 前端JS检查是否为空
      if (!this.user.username || !this.user.password)
      {
        return this.$message.error("用户名或密码不能为空");
      }
        // 发送登录请求
        this.request.post("/user/login", this.user).then(res => {
          if (res.code === '200') {
            // this.$message.success("欢迎回来"+ res.data.nickname);
            this.$message.success(res.data.nickname + " ,欢迎回来!");
            localStorage.setItem("user", JSON.stringify(res.data))   // 存储信息至浏览器
            this.$router.push("/")
            //window.open('/user')      // 登录成功新打开窗口（例如用户信息页）
            document.getElementById('login').classList.remove('show-login')
          } else {
            this.$message.error(res.msg)
          }
        }).catch(error =>{
          // 检测无后端传回提示出错
          console.error(error);
          this.$message.error("网络异常!")
        })
    },
    // SUBMIT REGISTER FORM
    Register(event){
      event.preventDefault();

      // 前端js判断信息填写
      if (!this.user1.username || !this.user1.password || !this.user1.password1 || !this.user1.email || !this.user1.nickname)
      {
        return this.$message.error("请完整填写信息");
      }
      if (this.user1.password !== this.user1.password1){
        return this.$message.error("两次密码不一致");
      }
      if (!/^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+)$/.test(this.user1.email)){
        this.$message.error("请输入正确的邮箱号");
      }
      else {
        // 发送请求
        this.request.post("/user/register", this.user1).then(res => {
          if (res.code === '200') {
            this.$message.success("注册成功，页面在2秒后切换登录");
            setTimeout(() => {
              this.currentForm = 'login';
            }, 2000)
          } else {
            this.$message.error(res.msg);
          }
        })
      }
    },



    /* ===================== SHOW LOGGING MENU ================ */
    isLoginClick() {
      const userInfo = JSON.parse(localStorage.getItem('user'))
      if (userInfo){
        window.open('/user/home')
      }else {
        document.getElementById('login').classList.add('show-login')
      }
    },

    /* ====================== HIDDEN LOGGING MENU ============== */
    isLoginClose() {
      document.getElementById('login').classList.remove('show-login');
      // 延时操作
      setTimeout(() =>{
        this.currentForm = 'login';

        // 关闭表单后清空数据
        this.user1.username = '';
        this.user1.password = '';
        this.user1.password1 = '';
        this.user1.email = '';
        this.user1.nickname = '';

      }, 500)

    },

    /* ==================== CHANGE LOGGING FORM =============== */
    switchForm(formType){
      this.currentForm = formType;
    }
  }

}
</script>

<style scoped>
/* ========================= CONTAINER =========================== */
.class__container{
  height: 100%;
  width: 100%;
}
.header__container{
  width: 100%;
  /*height: calc(var(--header-height) + 2rem);*/
  height: 90px;
  position: fixed;
  top: 0;
  left: 0;
  /*background-color: rgba(128, 128, 128, 0.5) ;*/
  background: linear-gradient(to bottom, rgba(0, 0, 0, 0.5), transparent);
  /*box-shadow: 0 2px 16px hsla(230, 75%, 32%, 0.15);*/

}
.change__container{
  width: 100%;
  height: 100%;
  overflow: hidden;
}
.nav__container{
  position: fixed;
  left: 1280px;
  width: 256px;
}
.page__change__container{
  position: absolute;
  width: 150px;
  height: 80px;
  top: 50%;
  left: 50px;
}

/* ================================ HEADER & NAV ============================= */

.logo{
  margin-left: 1rem;
  width: 7rem;
  /*margin-right: auto;*/
}

.nav{
  height: var(--header-height);
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.nav__link{
  text-decoration: none;
  color: white;
  font-weight: var(--font-semi-bold);
  transition: color 0.4s;
}
.nav__list{
  list-style: none;         /*去除浏览器链接默认圆点样式*/
  display: flex;
  flex-direction: column;
  row-gap: 2.5rem;
  text-align: center;
}
.nav__actions{
  display: flex;
  align-items: center;
  column-gap: 1rem;
  padding: 10px;
}
.nav__login{
  font-size: 26px;
  color: white;
  cursor: pointer;
  transition: color 0.4s;
}

@media screen and (min-width: 1023px){
  .nav{
    height: calc(var(--header-height) + 2rem);
    column-gap: 3rem;
  }
  .nav__close,
  .nav__toggle{
    display: none;
  }
  .nav__menu{
    margin: auto;
  }
  .nav__list{
    flex-direction: row;
    column-gap: 3rem;
  }

}

/* ==================== LOGGING FORM ====================== */
.login__form,
.register
{
  display: grid;
}

.login{

  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: var(--z-modal);
  background-color: hsla(230, 75%, 32%, 0.1);
  backdrop-filter: blur(24px);
  -webkit-backdrop-filter: blur(24px);    /* safari web */
  padding: 8rem 1.5rem 0;
  opacity: 0;
  pointer-events: none;
  transition: opacity 0.4s;
}

.login__form{
  max-width: 500px;
  margin: 0 auto;
  background-color: var(--container-color);
  padding: 2rem 1.5rem 2.5rem;
  box-shadow: 0 8px 32px hsla(230, 75%, 15%, .2);
  border-radius: 1rem;
  row-gap: 1.25rem;
  text-align: center;
  transform: translateY(-1rem);
  transition: transform 0.4s;
}

.login__title{
  font-size: var(--h2-font-size);
  color: var(--title-color);
}

.login__group{
  row-gap: 1rem;
}

.login__input{
  width: 90%;
  background-color: var(--container-color);
  border: 2px solid var(--border-color);
  padding: 1rem;
  border-radius: .5rem;
  color: var(--text-color);
  margin: 10px;
}

.login__input::placeholder{
  color: var(--text-color);

}
.login__signup{
  margin-bottom: .5rem;
}
.login__signup a{
  color: var(--first-color);
}
.login__forgot{
  display: inline-block;
  color: var(--first-color);
  margin-bottom: 1.25rem;
}

.login__button{
  display: inline-block;
  background-color: var(--first-color);
  width: 90%;
  color: #fff;
  font-weight: var(--font-semi-bold);
  padding: 0.75rem 1rem;
  border-radius: .5rem;
  cursor: pointer;
  transition: box-shadow .4s;
}
.login__button:hover{
  box-shadow: 0 4px 24px hsla(230, 75%, 40%, .4);
}

.show-login{
  opacity: 1;
  pointer-events: initial;
}

.show-login .login__form{
  transform: translateY(0);
}
.login__form{
  padding: 3rem 2rem 3.5rem;
}

.login__close{
  position: absolute;
  top: 2rem;
  right: 2rem;
  font-size: 1.5rem;
  color: var(--title-color);
  cursor:pointer;
}

@media screen and (min-width: 1150px) {
  .container{
    margin-inline: auto;
  }


}

/* ====================== 滑动效果动画定义 ================================= */

/* 定义进入和离开动画的持续时间和缓动函数 */
.slide-enter-active, .slide-leave-active {
  transition: transform 2s ease, opacity 2s ease;
}

/* 定义新页面进入时的初始和结束状态 */
.slide-enter, .slide-enter-to {
  position: fixed;
  top: 0;
  /*top: 56px;*/
  left: 0;
  height: 100%;
  overflow: hidden;
  z-index: -1;
  background-color: black;
}

/* 新页面开始时从完全透明和屏幕右侧进入 */
.slide-enter {
  opacity: 0.5;
  transform: translateX(100%);
}

/* 新页面结束时完全不透明并位于屏幕中央 */
.slide-enter-to {
  opacity: 1;
  transform: translateX(0);
}

/* 当前页面离开时从屏幕中央向左移动并变为透明 */
.slide-leave-to {
  opacity: 0.5;
  transform: translateX(-100%);
}

/* =================== OTHER CSS ========================= */

/*        鼠标停驻颜色       */
:is(.nav__login, .nav__link):hover,
:is(.nav__login, .nav__link):focus,
:is(.nav__login, .nav__link):active{
  color: rgb(25, 209, 255);
}


.click__color{
  color: rgb(25, 209, 255);
}

/* ============== Aside ================= */
.aside{
  position: absolute;
  height: 100%;
  width: 252px;
  right: 0;
  /*border-left: .1px solid  white;*/
  box-shadow: inset .3px 0 0 white;

}


</style>