<template>
  <div>
    <el-container style="height: 100vh;">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <Aside/>
      </el-aside>

      <el-container>
        <el-header style="border-bottom: 1px solid #ccc;">
          <Header />
        </el-header>

        <el-main>

          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>

</template>


<script>

import Aside from "@/views/manager/components/Aside";
import Header from "@/views/manager/components/Header";

export default {
  name: "manage",
  data(){
    return {

    }
  },

  components:{
    Aside,
    Header,
  }
}
</script>

<style scoped>

</style>