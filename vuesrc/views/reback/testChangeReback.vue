<template>

  <div class="example">
    <div class="router__example">
      <h2>页面切换过渡</h2>
      <p>
        <router-link to="/test/a" class="router__link__btn">切换A</router-link>
        <router-link to="/test/b" class="router__link__btn">切换B</router-link>
        <router-link to="/test/c" class="router__link__btn">切换C</router-link>
      </p>

      <!--      <router-view v-slot="{Component}">-->
      <!--        <transition name="fade">-->
      <!--          <component :is="Component" class="z__child" />-->
      <!--        </transition>-->
      <!--      </router-view>-->
    </div>
    <div class="color__container">
      <transition name="slide" >
        <router-view/>
      </transition>
    </div>

  </div>



</template>

<script lang="ts">
import {defineComponent} from "vue";

export default  {
  name: "test"
}
</script>

<style   scoped>
.example {
  height: 100%;
  width: 100%;
  text-align: center;
  position: relative;
  overflow: hidden;
}
.color__container {
  width: 100%; /* 设置容器宽度 */
  margin-top: 20px; /* 设置与切换区域的间距 */
  background-color: white; /* 设置默认背景颜色 */
}
.router__example{
  margin-top: 30px;
}
.router__link__btn{
  display: inline-block;
  padding: 10px 15px;
  border: 2px solid #ccc;
  text-decoration: none;
  color: initial;
  margin-right: 10px;
  margin-top: 20px;
  border-radius: 4px;
}
/* ======== 鼠标点击时变色，松开恢复 ======= */
/*.router__link__btn:active{*/
/*  color: seagreen;*/
/*  border-color: seagreen;*/
/*}*/
.router__link__btn.router-link-active{
  color: seagreen;
  border-color: seagreen;
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





.width-change-enter-active, .width-change-leave-active {
  transition: width 2s ease;
}
.width-change-enter, .width-change-leave-to /* 页面离开时的状态 */ {
  width: 0;
}
.width-change-enter-to /* 新页面进入时的结束状态 */ {
  width: 100%;
}
.width-change-leave /* 当前页面离开时的初始状态 */ {
  width: 100%;
}


.z__child{
  transition: .8s cubic-bezier(.55,0,.1,1);
  position: absolute;
  bottom: 0;
  width: 100%;
  min-height: 60vh;
  background-color: black;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 48px;
}



</style>