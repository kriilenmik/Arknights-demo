<template>
  <div class="wrapper">
    <div style="margin:200px auto; background-color: #fff ; width: 400px; height: 300px; padding: 20px; border-radius: 10px; opacity: 0.85">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>登 录</b></div>

      <el-form :rules="rules" :model="user" ref="userForm">
        <el-form-item prop="username" >
          <el-input prop="username" size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.username" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input  size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.password" placeholder="密码"></el-input>
        </el-form-item>
        <div style="margin: 10px 0;text-align: center">
          <el-button type="primary" size="small" autocomplete="off" @click="Login">登录</el-button>
        </div>

      </el-form>

    </div>

  </div>

</template>

<script>
export default {
  name: "managerLogin",
  data(){
    return{
      user:{},
      rules:{
        username:[
          {required: true, message: '请输入用户名', trigger: ['blur','change']}
          // {min: 3, max: 5, message: '长度3~5', trigger: 'blur' }
        ],
        // password对应input框的prop，上同, blur意思鼠标失焦触发
        password:[
          {required: true, message: '请输入密码', trigger: ['blur','change']}
          // {min: 3, max: 5, message: '长度6', trigger: 'blur' }
        ]
      }
    };
  },
  methods:{
    Login(){
      this.$refs['userForm'].validate((valid) => {
        if (valid) {    //表单校验合法
          this.request.post("/manager/login",this.user).then(res =>{
            if(res.code === '200'){
              this.$message.success("登录成功")
              this.$router.push("/manager/home")
            }else {
              this.$message.error(res.msg)
            }
          })
        }else {return this.$message.error("用户名或密码不能为空")}
      });

    }
  }
}
</script>

<style >
.wrapper{
  height: 100vh;
  background-image: url("~@/assets/logback.png");
  overflow: hidden;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}


</style>

