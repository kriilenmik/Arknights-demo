<template>
  <el-menu :default-openeds="['1', '3']" style="min-height: 100%;overflow-x: hidden"
           background-color="rgb(48,65,86)"
           text-color="#fff"
           active-text-color="#ffb7c5"
           router
  >
    <div style="height: 60px;line-height: 60px;text-align: center">
      <img src="../../../assets/logo.png" alt="" style="width: 20px;position: relative;top:5px;margin-right: 5px">
      <b style="color: white">后台管理系统</b>

    </div>
    <el-menu-item index="/manager/home">
      <template slot="title">
        <i class="el-icon-house"></i>
        <span slot="title">主页</span>
      </template>
    </el-menu-item>
    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span slot="title">系统管理</span>
      </template>
      <el-menu-item index="/manager/user">
        <i class="el-icon-s-custom"></i>
        <span slot="title">用户管理</span>
      </el-menu-item>
      <el-submenu index="2-1">
        <template slot="title">
          <i class="el-icon-tickets"></i>
          <span slot="title">公告管理</span>
        </template>
      <el-menu-item index="/manager/information">
        <i class="el-icon-s-order"></i>
        <span slot="title">公告信息</span>
      </el-menu-item>
        <el-menu-item index="/manager/picture">
        <i class="el-icon-picture"></i>
        <span slot="title">走马灯图</span>
      </el-menu-item>
      </el-submenu>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props:{
    isCollapsed: Boolean,
    logoTextShow: Boolean,
  }
}
</script>

<style scoped>

</style>