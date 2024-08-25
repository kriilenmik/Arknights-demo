<template >
  <div>
    <div>
    <!-- ==========  HEADER   =================== -->
    <header class="header" id="header">
      <nav class="nav container">
        <a href="#" class="nav__logo">Logo</a>
        <!-- ============= HADER_Menu ================= -->
        <div class="nav__menu" id="nav-menu" :class="{'active':isMenuVisible}">
          <ul class="nav__list">

            <li class="nav__item">
              <a class="nav__link">
                <router-link to="/index" class="router__link__btn nav__link">Home</router-link>
              </a>
            </li>

            <li class="nav__item">
              <a class="nav__link">
                <router-link to="/index/b" class="router__link__btn nav__link">About us</router-link>
              </a>
            </li>

            <li class="nav__item">
              <a class="nav__link">
                <router-link to="/index/a" class="router__link__btn nav__link">Services</router-link>
              </a>
            </li>

            <li class="nav__item">
              <a class="nav__link">
                <router-link to="/index/c" class="router__link__btn nav__link">Featured</router-link>
              </a>
            </li>

            <li class="nav__item">
              <a href="#" class="nav__link">Contact Us</a>
            </li>
          </ul>

          <!-- ===================== CLOSE_Button ================  -->
          <div class="nav__close" id="nav-close" @click="Close_menu">
            <i class="el-icon-close"></i>
          </div>
        </div>

          <!-- ==================== BUTTON =============== -->
        <div class="nav__actions">
          <!-- =========== SEARCH_Button ========== -->
          <i class="el-icon-search nav__search" id="search-btn" @click="isSearchClick"></i>

          <!-- =========== LOGIN_User_Button ========== -->
          <i class="el-icon-user nav__login" id="login-btn" @click="isLoginClick"></i>

          <!-- =========== TOGGLE_Button ========== -->
          <i class="el-icon-menu nav__toggle" id="nav-toggle" @click="Toggle_menu"></i>
        </div>
      </nav>
    </header>

    <!-- =========================== CHANGE_Container ================== -->

    </div>
    <div class="page__container">
      <transition name="slide" >
        <router-view/>
      </transition>
    </div>

    <!-- ============================ SEARCH_Page ===================== -->
    <div class="search" id="search">

      <form action="" class="search__form">
        <i class="el-icon-search search__icon"></i>
<!--        <i class="el-icon-search search__icon"></i>-->
<!--        <el-form-item>-->
<!--        <el-input type="search" placeholder="输入内容" class="search__input"></el-input>-->
<!--        </el-form-item>-->
        <input placeholder="输入内容" class="search__input" >
      </form>
      <i class="el-icon-close search__close" id="search-close" @click="isSearchClose"></i>
    </div>


    <!-- =============== LOGIN_Page   =================== -->
    <div class="login" id="login">
      <form action="" class="login__form">
        <h2 class="login__title">登 录</h2>
        <div class="login__group">
          <div>
            <input type="text" placeholder="请输入用户名" id="username" class="login__input">
          </div>

          <div>
            <input type="password" placeholder="请输入密码" id="password" class="login__input">
          </div>
        </div>
        <div>
          <p class="login__signup">
            还没有账号？ <a href="#">立即注册</a>
          </p>

          <a href="#" class="login__forgot">
            忘记密码
          </a>

          <button type="submit" class="login__button">登录</button>
          </div>
      </form>

      <i class="el-icon-close login__close" id="login-close" @click="isLoginClose"></i>
    </div>

    <!-- =========  MAIN_Page   ==============  -->
<!--    <main class="main">-->
<!--      <img src="../assets/previ.png" alt="image" class="main__bg" />-->
<!--    </main>-->



  </div>
</template>

<script>
export default {
  name: "log",
  mounted() {

  },
  data(){
    return{
      isMenuVisible:false,

    };
  },
  methods:{
    /* ===================== SHOW MENU ================== */
    Toggle_menu(){
      this.isMenuVisible = !this.isMenuVisible;
    },

    /* =================== CLOSE MENU ==================== */
    Close_menu(){
      this.isMenuVisible = !this.isMenuVisible;
    },

    /* ==================== SHOW SEARCH ================== */
    isSearchClick(){
      document.getElementById('search').classList.add('show-search')
    },

    /* ==================== HIDDEN SEARCH =================== */
    isSearchClose(){
      document.getElementById('search').classList.remove('show-search')
    },

    /* ===================== SHOW LOGGING MENU ================ */
    isLoginClick(){
      document.getElementById('login').classList.add('show-login')
    },

    /* ====================== HIDDEN LOGGING MENU ============== */
    isLoginClose(){
      document.getElementById('login').classList.remove('show-login')
    }
  }
}
</script>

<style scoped>
  *{
    box-sizing: border-box;
    padding: 0;
    margin: 0;
  }
  html{
    scroll-behavior: smooth;
  }

  .page__container{
    background-color: #ffffff;
    width: 100%;
    height: auto;
  }
  .search,
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


  /* ==================================== HEADER & NV ======================================*/
  .header{
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    background-color: rgba(128, 128, 128, 0.5) ;
    box-shadow: 0 2px 16px hsla(230, 75%, 32%, 0.15);
    z-index: var(--z-fixed);
  }
  .nav{
    height: var(--header-height);
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .nav__logo{
    color: var(--title-color);
    font-weight: var(--font-semi-bold);
    transition: color 0.4s;
    text-decoration: none;              /*浏览器默认给链接加下划线，此代码去除*/
  }
  .nav__actions{
    display: flex;
    align-items: center;
    column-gap: 1rem;
  }
  .nav__search,
  .nav__login,
  .nav__toggle,
  .nav__close{
    font-size: 1.25rem;
    color: var(--title-color);
    cursor: pointer;
    transition: color 0.4s;
  }

  .nav__link{
    text-decoration: none;
    color: var(--title-color);
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

  /* =========== 鼠标放置图标上有背景颜色 =========== */
  :is(.nav__logo, .nav__search, .nav__login, .nav__toggle, .nav__link):hover{
    color: var(--first-color);
  }

  /* ============================== Navigation for mobile devices  =============================*/
  @media screen and (max-width: 1023px){
    .nav__menu{
      position: fixed;
      right: -100%;
      top: 0;
      /*background-color: var(--body-color);*/
      background-color: rgba(255, 255, 255, 0.5);
      box-shadow: 0 8px 16px hsla(230, 75%, 32%, 0.15);
      width: 100%;
      padding-block: 4.5rem 4rem;
      transition: right 0.7s;
    }
  }
  .nav__menu.active{
    right: 0;
  }
  .nav__close{
    position: absolute;
    top: 1.15rem;
    right: 1.5rem;
  }
  /*========================== SEARCH CSS =========================== */
  .search__form{
    display: flex;
    align-items: center;
    column-gap: 0.5rem;
    background-color: var(--container-color);
    box-shadow: 0 8px 32px hsla(230, 75%, 15%, 0.2);
    padding-inline: 1rem;
    border-radius: 0.5rem;
    transform: translateY(-1rem);
    transition: transform 0.4s;
  }
  .search__icon{
    font-size: 1.25rem;
    color: var(--title-color);
  }
  .search__input{
    width: 100%;
    height: 100%;
    padding-block: 1rem;
    background-color: var(--container-color);
    color: var(--text-color);
    border: none;
    outline: none;
    font-size: large;
  }
  .search__input::placeholder{
    color: var(--text-color);
    font-size: large;
  }
  /*.search__input:focus{*/
  /*  outline: none;*/
  /*  border: none;*/
  /*  -webkit-appearance: none;*/
  /*}*/

  /* =============================== SHOW SEARCH ========================== */
  .show-search{
    opacity: 1;
    pointer-events: initial;
  }
  .show-search,
  .search__form{
    transform: translateY(0);
  }

  .search__close,
  .login__close{
    position: absolute;
    top: 2rem;
    right: 2rem;
    font-size: 1.5rem;
    color: var(--title-color);
    cursor:pointer;
  }

  .main{
    height: 100vh;
    position: relative;
  }
  .main__bg{
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
    object-position: center;
    z-index: -1;
  }
  .container{
    max-width: 1120px;
    margin-inline: 1.5rem;
  }

  /* ====================== LOGIN FORM CSS ====================== */

  .login__form,
  .login__group{
    display: grid;
  }

  .login__form{
    /*max-width: 500px;*/
    /*margin: 0 auto;*/
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
    width: 100%;
    background-color: var(--container-color);
    border: 2px solid var(--border-color);
    padding: 1rem;
    border-radius: .5rem;
    color: var(--text-color);
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
    width: 100%;
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

  /* =================== SHOW LOGGING ================================= */
  .show-login{
    opacity: 1;
    pointer-events: initial;
  }

  .show-login .login__form{
    transform: translateY(0);
  }

  /* ======================= BREAKPOINTS ========================== */
  @media screen and (min-width: 576px){
    .search,
    .login{
      padding-top: 10rem;
    }

    .search__form{
      max-width: 450px;
      margin-inline: auto;
    }

    .search__close,
    .login__close{
      width: max-content;
      top: 5rem;
      left: 0;
      right: 0;
      margin-inline: auto;
      font-size: 2rem;
    }

    .login__form{
      max-width: 400px;
      margin-inline: auto;
    }
  }

  /* ==================== LARGE DEVICE ========================== */
  @media screen and (min-width: 1023px){
    .nav{
      /*height: calc(var(--header-height) + 2rem);*/
      column-gap: 3rem;
    }
    .nav__close,
    .nav__toggle{
      display: none;
    }
    .nav__menu{
      margin-left: auto;
    }
    .nav__list{
      flex-direction: row;
      column-gap: 3rem;
    }

    .login__form{
      padding: 3rem 2rem 3.5rem;
    }
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
    position: absolute;
    height: 100%;
  }

  /* 新页面开始时从完全透明和屏幕右侧进入 */
  .slide-enter {
    opacity: 0;
    transform: translateX(100%);
  }

  /* 新页面结束时完全不透明并位于屏幕中央 */
  .slide-enter-to {
    opacity: 1;
    transform: translateX(0);
  }

  /* 当前页面离开时从屏幕中央向左移动并变为透明 */
  .slide-leave-to {
    opacity: 0;
    transform: translateX(-100%);
  }

</style>