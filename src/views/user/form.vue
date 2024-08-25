<template>
  <div class="common__container">

    <div class="content">

      <!--        NOW ACCOUNT SHOW     -->
      <diiv class="userAccount">
        <div class="userAccount__label">当前账号</div>
        <div class="userAccount__name">{{username}}</div>
      </diiv>

      <!--        ACCOUNT INFO SHOW ---- >> avatar // username // mail-->
      <div class="index__card__display index__card">
        <div style="display: block; margin-bottom: 1em; margin-top: -15px">
          <div class="el-icon-user" style="width: 1.2em; height: 1.5em; display: inline-block;"></div>
          账号信息
        </div>
        <div style="padding: 0 1.8em">
          <div style="margin: 16px 0">
            <div style="color: #158fc5; margin: .5em 0; font-size: 16px">绑定邮箱</div>
            <div class="user__mail">{{email}}</div>
          </div>

        </div>
      </div>
      <div class="index__card__display index__card">
        <div style="display: block; margin-bottom: 1em; margin-top: -15px">
          <div class="el-icon-s-order" style="width: 1.2em; height: 1.5em; font-size: 24px; color: #158fc5; display: inline-block;"></div>
          账号角色信息
        </div>
        <div style="padding: 0 1.8em">
          <div style="margin: 16px 0">
            <div style="color: #158fc5; margin: .5em 0; font-size: 16px">角色登录用户名</div>
            <div class="user__mail">{{username}}</div>
          </div>
        </div>
        <div style="padding: 0 1.8em">
          <div style="margin: 16px 0">
            <div style="color: #158fc5; margin: .5em 0; font-size: 16px">角色昵称</div>
            <div class="user__mail">{{nickname}}</div>
          </div>
        </div>


      </div>

    </div>

  </div>
</template>

<script>
export default {
  name: "form",
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

.nav__container nav{
  flex-grow: 0;
}
.common__container{
  position: relative;
  flex-grow: 1;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.index__nav__Logo img{
  max-height: 100%;
  max-width: 100%;
  display: block;
  margin: auto;
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