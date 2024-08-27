<template>
  <div class="common__container">

    <div class="content">

      <!--        ACCOUNT INFO SHOW ---- >> avatar // username // mail-->
      <div class="index__card__display index__card">
        <div class="card__text1" style="">
          请按照流程完成账号注销
        </div>
        <div style="display: flex">
          <div style="padding: 24px 1.8em 0;width: 30%; border-right: 2px solid #e0e0e0">
            <div style="margin: 0 0 16px">

              <div style="height: 300px;">
                <el-steps direction="vertical" :active="active" finish-status="success">
                  <el-step title="确认注销条件"></el-step>
                  <el-step title="确认申请注销"></el-step>
                  <el-step title="注销完成"></el-step>
                </el-steps>
              </div>
            </div>


          </div>
          <div class="change__container" v-if="currentPage === 'page0'">
            <div style="padding: 24px 1.8em 0; ">
              <div style="margin-bottom: 5px">

                <div style="align-items: center; display: flex; flex-direction: column; position: relative;">
                  <div style="align-items: center; display: flex; flex-direction: column">
                    <div style="font-size: 1.5em; margin-bottom: .5em; font-weight: bold">账号注销须知</div>
                    <div style="font-size: .85em; margin-bottom: 1em; text-align: center">该操作将删除通行证下所有的数据，实际删除操作执行后，通行证及相关产品数据将无法恢复，请谨慎进行操作。</div>
                  </div>
                </div>

                <div style="margin: 1em 0 .8em;">
                  <div style="align-items: center; display: flex; justify-content: center; ">
                  <el-checkbox v-model="checked"></el-checkbox>
                    <span>操作前请仔细阅读并同意</span>
                    <a class="delete__text" href="https://user.hypergryph.com/protocol/plain/ak/privacy" target="_blank">《通行证账号注销协议》</a>
                  </div>


                  <div v-if="!checked && submitted" class="el-icon-warning error">您还没有同意我们的条款</div>
                </div>


            <div style="display: flex; justify-content: center">
              <el-button style="margin-top: 12px; width: 35%; height: 3.5em" @click="handleClick1">下一步</el-button>

            </div>
              </div>
            </div>

          </div>

          <!-- ======================================================================================== -->


          <div class="change__container" v-if="currentPage === 'page1'">
            <div style="font-size: 1.5em; margin-bottom: .5em; font-weight: bold; display: flex; justify-content: center">账号信息核验</div>
            <div style="padding: 0 1.8em">
              <div style="margin: 16px 0">
                <div style="color: #158fc5; margin: .5em 0; font-size: 16px">角色用户名</div>
                <div class="user__mail">{{username}}</div>
              </div>
            </div>
            <div style="padding: 0 1.8em">
              <div style="margin: 16px 0">
                <div style="color: #158fc5; margin: .5em 0; font-size: 16px">绑定邮箱</div>
                <div class="user__mail">{{email}}</div>
              </div>
            </div>
            <div style="padding: 0 1.8em">
              <div style="margin: 16px 0">
                <div style="color: #158fc5; margin: .5em 0; font-size: 16px">角色昵称</div>
                <div class="user__mail">{{nickname}}</div>
              </div>
            </div>
            <div style="display: flex; justify-content: center">

              <el-button style="margin-top: 12px; width: 35%; height: 3.5em" @click="dialogVisible = true">确认注销</el-button>

              <el-dialog
                  title="提示"
                  :visible.sync="dialogVisible"
                  width="30%"
                  :before-close="handleClose">
                <span>确认注销账号？注销将无法恢复</span>
                <span slot="footer" class="dialog-footer">
                  <el-button @click="dialogVisible = false">取 消</el-button>
                  <el-button type="primary" @click="handleConfirm">确 定</el-button>
                </span>
              </el-dialog>

            </div>
          </div>



          <!-- ======================================================================================== -->

          <!-- ======================================================================================== -->


          <div class="change__container" v-if="currentPage === 'page2'">
            <div style="display: grid; justify-items: center">
              <el-col>
                <el-result icon="success" title="注销操作成功" subTitle="页面即将刷新，返回主页">
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
  name: "user-delete",
  data(){
    return {
      checked: false,
      submitted: false,
      email: 'Null',
      active: 0,
      currentPage: 'page0',
      nickname: '',
      username: '',
      dialogVisible: false,
    };
  },

  created() {
    const user = JSON.parse(localStorage.getItem('user'));
    this.email = user.email;
    this.username = user.username;
    this.nickname = user.nickname;

  },
  computed:{

  },

  methods: {
    handleClick1(){
      this.submitRead();
    },
    handleConfirm(){
      this.dialogVisible = false
      this.submitDelete()
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
      else if (this.currentPage === 'page2'){
        this.currentPage = 'page3'
      }
    },

    submitRead(){
      this.submitted = true
      if (this.checked){
        this.next()
      }
    },

    submitDelete(){
      this.request.delete("user/userDelete/" + this.username).then(res => {
        if (res.code === '200'){
          this.$message.success("注销用户操作成功")
          this.next()
          this.active++
          setTimeout(() => {
            this.$message.warning("页面信息已过期")
            localStorage.removeItem("user")
            this.$router.push("/")
          }, 3000)
        }else {
          this.dialogVisible = false
          this.$message.error(res.msg)}
      })
    },

    handleClose(done){
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
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
.delete__text{
  text-decoration: none;
  color: black;
}
.delete__text:hover{
  color: #0098dc;
}
.error{
  align-items: center;
  display: flex;
  justify-content: center;
  margin-top: .5em;
  color: red;
}
</style>