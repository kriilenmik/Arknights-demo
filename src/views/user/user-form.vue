<template>
  <div class="container">



    <nav class="nav__container">
      <div class="index__nav">

        <div class="index__nav__menu" style="z-index: 150">
          <i v-if="isMenuOpen" class="el-icon-s-operation" style="font-size: 35px; line-height: 1.75em; cursor: pointer" @click="toggleMenu"></i>
          <i v-else class="el-icon-close" style="font-size: 35px; line-height: 1.75em; cursor: pointer; color: white"  @click="toggleMenu"></i>
        </div>

        <div class="index__nav__Logo">
          <img src="../../assets/ark.png">
        </div>

        <div class="index__nav__extra">
          <div class="index__nav__out">
            <div class="el-icon-s-release" @click="logOut"></div>
            <div class="index__nav__outLog" @click="logOut">登出</div>
          </div>
          <div class="index__nav__out">
            <div class="el-icon-s-promotion" @click="returnBack"></div>
            <div class="index__nav__outLog" @click="returnBack">返回官网</div>
          </div>

        </div>
      </div>

      <div class="toggle__container1 toggle__change1 toggle__change" :class="before">

        <div class="menu__header"></div>

        <div class="toggle__menu">
          <div class="menu__content">

            <a class="menu__link"  v-bind:class="animationClass">
              <div class="menu__item"  @click="closeMenu">
                <div class="el-icon-user" style="color: white; font-size: 1.75em; margin-right: 10px"></div>
                <span class="menu__font">基本信息</span>
              </div>
            </a>
            <a class="menu__link" v-bind:class="animationClass">
              <div class="menu__item" @click="closeMenu1">
                <div class="el-icon-lock" style="color: white; font-size: 1.75em; margin-right: 10px"></div>
                <span class="menu__font">密码重置</span>
              </div>
            </a>

            <a class="menu__link" v-bind:class="animationClass">
              <div class="menu__item" @click="closeMenu2">
                <div class="el-icon-close" style="color: white; font-size: 1.75em; margin-right: 10px"></div>
                <span class="menu__font">账号注销</span>
              </div>
            </a>

          </div>
        </div>


      </div>

    </nav>

    <div class="change">
      <router-view/>
    </div>


  </div>
</template>

<script>


export default {
  name: "user-form",
  data(){
    return{
      isMenuOpen: 'false',
      username: 'Null',
      email: '未绑定邮箱！！',
      nickname: '默认用户名',
    };
  },
  created() {
    const user = JSON.parse(localStorage.getItem('user'));
    this.username = user.username;
    this.email = user.email;
    this.getInfo();
  },
  computed:{
    before(){
      return this.isMenuOpen ? '' : 'toggle__container';
    },
    animationClass(){
      return this.isMenuOpen ? 'item__close' : 'item__show';
    },
  },

  methods:{
    toggleMenu(){
      this.isMenuOpen = !this.isMenuOpen;
    },
    closeMenu(){
      this.isMenuOpen = !this.isMenuOpen;
      this.$router.push("/user/home")
    },
    closeMenu1(){
      this.isMenuOpen = !this.isMenuOpen;
      this.$router.push("/user/resetPwd")
    },
    closeMenu2(){
      this.isMenuOpen = !this.isMenuOpen;
      this.$router.push("/user/deleteUser")
    },
    closeMenu3(){
      this.isMenuOpen = !this.isMenuOpen;
      this.$router.push("/user/simulator1")
    },
    logOut(){
      localStorage.removeItem("user");
      this.$router.push("/")
    },
    returnBack(){
      this.$router.push("/")
    },
    getInfo(){
      this.request.get("/user/find",{
        params:{
          username: this.username
        }
      })
      .then(res =>{
        this.nickname = res.data.nickname
      })
    },
  }

}
</script>

<style scoped>

/* ========= CONTAINER =========== */
.container{
  position: relative;
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
  background-image: url("../../assets/home.png");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: bottom;
}
.nav__container nav{
  flex-grow: 0;
}

.change{
  position: relative;
  flex-grow: 1;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.toggle__container1{

  z-index: 100;
  position: absolute;
  display: flex;
  flex-direction: column;
  max-width: 430px;
  overflow: hidden;
  top: 0;
}
.toggle__change1{
  height: 100%;
  width: 22%;
}
/* =========== NAV & MENU ========= */
.index__nav{
  display: flex;
  flex-direction: row;
  background-color: #fff;
  height: 5rem;
  padding: .5em 1em;
  box-shadow: 0 2px 5px -2px rgba(9, 2, 4, .8);
}
.index__nav__menu{
  width: 35%;
}
.index__nav__Logo{
  width: 30%;
  height: 100%;
  text-align: center;
}
.index__nav__Logo img{
  max-height: 100%;
  max-width: 100%;
  display: block;
  margin: auto;
}
.index__nav__extra{
  width: 35%;
  display: flex;
  justify-content: flex-end;
}
.index__nav__out{
  height: 100%;
  margin-top: 0.5em;
  margin-right: 3em;
  min-width: 4rem;
  display: flex;
  flex-direction: column;
  font-size: .8em;
  cursor: pointer;

}
.el-icon-s-release{
  font-size: 35px;
  text-align: center;
}
.el-icon-s-promotion{
  font-size: 35px;
  text-align: center;
}
.index__nav__outLog{
  font-size: 1em;
  text-align: center;
  line-height: 1em;
  width: 100%;
  white-space: nowrap;
}

/* ============ MAIN CONTENT ============= */
.content{
  width: 90%;
  max-width: 500px;
  margin: auto;
  flex-grow: 1;
}
.nav__container nav{
  flex-grow: 0;
}
/* ============ TOGGLE MENU ============= */
.toggle__menu,
.toggle__change{
  display: block;
}
.menu__content{
  width: 80%;
  margin-left: 2em;
  position: relative;
  transition: opacity .2s .3s;
}
.menu__header{
  height: 4.5em;
  padding: .5em 1em;
  flex-grow: 0;
  flex-shrink: 0;
}

.menu__link{
  color: #fff;
  text-decoration: none;
  display: block;
  overflow: hidden;
}
.menu__item{
  display: block;
  border-bottom: 1px solid hsla(0, 0%, 100%, .1);
  padding: 1em 0;
  text-align: left;
  min-height: 1em;
}
.menu__font{
  display: inline-block;
  overflow-block: hidden;
  white-space: nowrap;
  line-height: 1.5em;
  font-size: 20px;
}
.item__close{
  opacity: 0;
  pointer-events: none;
  visibility: hidden;
}
.item__show{
  opacity: 1;
}

.toggle__change:before{
  position: absolute;
  content: "";
  border-radius: 50%;
  width: 125vh;
  height: 125vh;
  min-width: 1000px;
  min-height: 1000px;
  transform: translate(-50%, -50%) scale(0);
  transition: all .35s ease-out;
  background-color: #0098dc;
  z-index: -1;
  top: 1.5em;
  left: 2em;
}
.toggle__container:before{
  transform: translate(-50%, -50%) scale(2);
}


/* ============== USER LABEL =========== */
.userAccount{
  background-color: #158fc5;
  color: white;
  height: 1.5em;
  padding: .25em 0;
  border-radius: 2em;
  box-sizing: content-box;
  width: 16em;
  margin: 1em auto 1em;
  white-space: nowrap;
  display: block;
}
.userAccount__label{
  border-right: 1px solid black;
  padding: 0 1em 0 1.5em;
}
.userAccount__name{
  padding: 0 2em 0 2em;
}
.userAccount__label,
.userAccount__name{
  display: inline-block;
}
.user__mail{
  display: block;
  width: 70%;
  height: 25px;
  margin: 0.5em ;
  border-bottom: 2px solid #e0e0e0;
}

/* ========== CARD SHOW ================= */
.index__card__display{
  margin: 2em auto 3em;
  padding: 1em;
}
.index__card{
  min-width: 20em;
  min-height: 8em;
  background-color: hsla(0, 0%, 100%, .8);
  border-radius: 1.2em;
  box-shadow: 0 2px 10px -5px rgba(9, 2, 4, .8);
  box-sizing: border-box;
  padding: 2em;
}

/* ================== OTHER CSS ================ */
.el-icon-user{
  color: #158fc5;
  font-size: 22px;
}
</style>