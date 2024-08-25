<template>
  <div class="common__container">

    <div class="content">

      <!--        ACCOUNT INFO SHOW ---- >> avatar // username // mail-->
      <div class="index__card__display index__card">
        <div class="card__text1" style="">
          请按照流程完成修改密码
        </div>
        <div style="display: flex">
        <div style="padding: 24px 1.8em 0;width: 30%; border-right: 2px solid #e0e0e0">
          <div style="margin: 0 0 16px">
<!--            <div style="color: #158fc5; margin: .5em 0; font-size: 16px">1234</div>-->
<!--            <div class="user__mail">{{email}}</div>-->

            <div style="height: 300px;">
              <el-steps direction="vertical" :active="active" finish-status="success">
                <el-step title="当前邮箱验证"></el-step>
                <el-step title="修改密码"></el-step>
                <el-step title="完成"></el-step>
              </el-steps>
            </div>
          </div>


        </div>
        <div class="change__container" v-if="currentPage === 'page0'">
          <div style="padding: 24px 1.8em 0; ">
            <div style="margin-bottom: 5px">
              <div style="font-weight: bold; font-size: large; display: flex; align-items: center;flex-direction: column">
                绑定邮箱: &nbsp;&nbsp;{{email}}
              </div>
            </div>

            <div style="font-size: small; display: flex; align-items: center; flex-direction: column">
              为保障账号密码安全，您需要通过验证当前账号绑定的邮箱。
            </div>

          </div>

          <div style="padding: 24px 1.8em 0; display: grid; justify-items: center">
            <div class="input-box">
              <input class="input-item" v-for="(item, index) in input" :key="index" v-model="input[index]"
                     @keyup="handleKeyUp(index, $event)" maxlength="1">
            </div>

          </div>

          <div style="display: grid; justify-items: center; margin: 2em 0; color: #0098dc;">
            <span style="cursor: pointer" @click="getEmailCode">获取验证码</span>
          </div>

          <div style="display: flex; justify-content: center">
            <el-button style="margin-top: 12px; width: 35%; height: 3.5em" @click="handleClick1">下一步</el-button>

          </div>

        </div>

          <!-- ======================================================================================== -->


          <div class="change__container" v-if="currentPage === 'page1'">
            <div style="padding: 24px 1.8em 0; ">
              <div style="margin-bottom: 5px">
                <div style="font-weight: bold; font-size: large; display: flex; align-items: center;flex-direction: column">
                  输入新的通行密码
                </div>
              </div>



            </div>

            <div style="padding: 24px 1.8em 0; display: grid; justify-items: center">

              <form style="width: 100%">
                <div style="margin-bottom: 1.5rem;">
                <div style="margin-bottom: 0.5rem;">新密码</div>
                <div style="height: 2em">
                <input type="password" placeholder="请输入新密码" spellcheck="false" style="height: 100%; width: 100%; font-size: 15px;" v-model="pass" >
                </div>
                </div>

                <div style="margin-bottom: 1em">
                <div style="margin-bottom: 0.5rem">确认密码</div>
                <div style="height: 2em">
                <input type="password" placeholder="请重新输入新密码" spellcheck="false" style="height: 100%; width: 100%; font-size: 15px" v-model="pass1">
                </div>
                </div>

              </form>

            </div>



            <div style="display: flex; justify-content: center">
              <el-button style="margin-top: 12px; width: 35%; height: 3.5em" @click="handleClick2">下一步</el-button>

            </div>

          </div>



          <!-- ======================================================================================== -->

          <!-- ======================================================================================== -->


          <div class="change__container" v-if="currentPage === 'page2'">
            <div style="display: grid; justify-items: center">
              <el-col>
                <el-result icon="success" title="操作成功" subTitle="页面即将刷新，请重新登录">
                </el-result>
              </el-col>
            </div>
          </div>



          <!-- ======================================================================================== -->


        </div>

      </div>

    </div>

  </div>
</template>

<script>
export default {
  name: "user-forget",
  data(){
    return {
      email: 'Null',
      active: 0,
      input: ['', '', '', ''],
      currentPage: 'page0',
      emailVerify: {},
      code: '',
      pass: '',
      pass1: '',
      user: {},
      password: '',
    };
  },

  created() {
    const user = JSON.parse(localStorage.getItem('user'));
    this.email = user.email;
  },
  computed:{
  },

  methods: {
    handleClick1(){
      this.submitCode();
    },
    handleClick2(){
      this.submitPass();
    },
    next() {
      if (this.active++ > 2) {
        this.active = 0;
      }
      if (this.currentPage === 'page0'){
        this.currentPage = 'page1'
      }else if (this.currentPage === 'page1'){
        this.currentPage = 'page2'
      }
    },
    submitCode(){
      this.emailVerify.code = this.input.join('')
      this.emailVerify.email = this.email

      this.request.post("/user/email/verify", this.emailVerify).then(res => {
        if (res.code === '200'){
          this.$message.success("验证成功!")
          this.next();
        }else{
          this.$message.error(res.msg)
        }
      })
    },

    submitPass(){
      this.user.email = this.email
      this.user.password = this.pass
      if (this.pass !== this.pass1){
        this.$message.error("两次密码输入不一致!")
      }else if(!this.pass || !this. pass1){
        this.$message.error("密码不能为空")
      }else {
        this.request.post("/user/email/change", this.user).then(res => {
          if (res.code === '200') {
            this.$message.success("修改成功!")
            this.next()
            this.active++
            setTimeout(() => {
              this.$message.warning("页面信息已过期，请重新登录")
              localStorage.removeItem("user")
              this.$router.push("/")
            }, 3000)
          }
        })
      }
    },

    handleKeyUp(index, event) {
      if (event.keyCode <= 47 || event.keyCode >= 58) {

        this.input.splice(index, 1, '');
      }
    },

    getEmailCode(){
      this.request.get("/user/email/" + this.email).then(res => {
        if (res.code === '200'){
          this.$message.success("邮件发送成功")
        }else{
          this.$message.error(res.msg)
        }
      })
    },


  },
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
.change__container{
  width: 70%;
}

/* =========== NAV & MENU ========= */

.index__nav__Logo img{
  max-height: 100%;
  max-width: 100%;
  display: block;
  margin: auto;
}

/* ============ MAIN CONTENT ============= */
.content{
  width: 90%;
  max-width: 800px;
  margin: auto;
  flex-grow: 1;
}
.nav__container nav{
  flex-grow: 0;
}

/* ========== CARD SHOW ================= */
.index__card__display{
  margin: 4em auto;
  padding: 1em;
}
.index__card{
  min-width: 20em;
  min-height: 8em;
  background-color: hsla(0, 0%, 100%, .8);
  border-radius: 1.2em;
  box-shadow: 0 2px 10px -5px rgba(9, 2, 4, .8);
  box-sizing: border-box;
  padding: 1em;
}
.card__text1{
  display: grid;
  justify-items: center;
  font-size: large;
  font-weight: bold;
  height: 60px;
  align-items: center;
  border-bottom: 2px solid #e0e0e0;
}

/* ================= INPUT ========= */
.input-box {
  display: flex;
  justify-content: space-between;
  width: 400px;
}
.input-item {
  width: 70px;
  height: 70px;
  text-align: center;
  font-size: 35px;
  font-weight: bold;
  outline: none;
  border: 1px solid #AAA;
}


/* ================== OTHER CSS ================ */

</style>