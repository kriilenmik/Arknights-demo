<template>
  <div style="font-size: 12px; line-height: 60px;display: flex">
    <div style="flex: 1;font-size: 20px">

      <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px">
        <el-breadcrumb-item :to="'/'">首页</el-breadcrumb-item>
        <el-breadcrumb-item >{{currentPathName}}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <el-dropdown style="width: 120px; cursor: pointer">
      <div style="display: inline-block"><meta name="referrer" content="no-referrer">
<!--        <img :src="user.avatarUrl" alt=""-->
<!--             style="width: 30px; border-radius: 50%; position: relative; top:10px; right: 5px">-->
        <span>{{user.nickname}}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      </div>
      <el-dropdown-menu slot="dropdown" style="width: 110px;text-align: center" >
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">个人信息</el-dropdown-item>
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <span style="text-decoration: none" @click="logout">退出</span>
        </el-dropdown-item>
      </el-dropdown-menu>
      <!--    <i class="el-icon-setting" style="margin-right: 15px"></i>-->
      <!--    <el-dropdown-menu slot="dropdown">-->
      <!--      <el-dropdown-item>查看</el-dropdown-item>-->
      <!--      <el-dropdown-item>新增</el-dropdown-item>-->
      <!--      <el-dropdown-item>删除</el-dropdown-item>-->
      <!--    </el-dropdown-menu>-->
    </el-dropdown>

  </div>
</template>

<script>
export default {
  name: "Header",
  computed: {
    currentPathName() {
      return this.$store.state.currentPathName;
    }
  },
  data(){
    return{
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  watch: {
    currentPathName(newVal, oldVal) {
      console.log(newVal)
    }
  },
  methods: {
    logout(){
      this.$router.push("/login")
      localStorage.removeItem("user")
      this.$message.success("成功退出登录")
    }
  }
}

</script>


<style scoped>

</style>