<template>
    <div class="main__container">
    <div class="app__download" style="display: flex">

      <a class="download__1" href="https://apps.apple.com/cn/app/明日方舟/id1454663939" target="_blank"  spellcheck="false">
        <div class="apple__icon">
          <img style="width: 90%" src="../../assets/apple_icon.jpg">
        </div>
        <div class="apple__text__container">
          <div class="apple__text__up">Apple Store</div>
          <div class="apple__text__up">苹果下载</div>
        </div>
      </a>

      <a class="download__2">
        <div class="android__icon">
          <img style="width: 90%"  src="../../assets/android_icon.jpg">
        </div>
        <div class="apple__text__container" @click="isClickDownload__1">
          <div class="apple__text__up">Android</div>
          <div class="apple__text__up">安卓下载</div>
        </div>
      </a>

<!--      <a class="download__1">-->
<!--        <div class="apple__icon">-->
<!--          <img style="width: 90%" src="../../assets/apple_icon.jpg">-->
<!--        </div>-->
<!--        <div class="apple__text__container">-->
<!--          <div class="apple__text__up">Apple Store</div>-->
<!--          <div class="apple__text__up">苹果下载</div>-->
<!--        </div>-->
<!--      </a>-->

    </div>
  <div class="video__container">
    <video ref="videoPlayer" src="../../assets/ak_preview_2.mp4" autoplay loop muted></video>
  </div>
  <div class="rhodes"></div>

  <div class="rhodes__text__container fadeInUp">
    <div class="rhodes__text__1">ARKNIGHTS</div>
    <div class="rhodes__text__2">
      <div class="rhodes__text__21 text__typing">RHODES ISLAND</div>
      <div class="rhodes__text__22 text__typing">HTTPS://AK.HYPERGRYPH.COM</div>
    </div>
  </div>


    </div>
</template>

<script>
export default {
  name: "indexVideo",
  data() {
    return {
      // show : true,
    }
  },
  mounted() {
    // this.show = true
  },
  // beforeRouteLeave(to, from, next){
  //   this.show = false;
  //   setTimeout(next, 100);
  // },
  methods: {
    isClickDownload__1(){
      setTimeout(() => {
        window.open('http://localhost:9090/manager/pic/download/Arknights.apk', '_blank')
      },200)
    }
  }

}
</script>

<style scoped>
.main__container{
  height: 100%;
  width: 100%;
  overflow: hidden;
}

/* ================= APP LOAD ==================== */
.app__download{
  position: fixed;
  width: 150px;
  /*height: 200px;*/
  right: 3rem;
  top: 430px;
  flex-direction: column;

}

.download__1, .download__2{
  cursor: pointer;
  display: flex;
  align-items: center;
  border: 1px solid transparent;
  border-radius: 0.25rem;
  width: 120px;
  height: 35px;
  margin: 1em 10px;
  transition: border 0.5s ease;
  text-decoration: none;
}
.download__1:hover{
  border: 1px solid  #fff;
}
.download__1{
  background-color: black;
}
.download__2{
background-color: rgb(142, 197, 27);
}
.download__2:hover{
  border: 1px solid #fff;
}
.apple__icon{
  width: auto;
  height: auto;

}
.apple__text__container{
  color: white;
  font-size: x-small;
  margin-left: 5px;
}




/* ================= VIDEO ===================== */

.video__container{
  position: relative;
  /*top: -20px;*/
  width: 100%;
  height: 100%;
  overflow: hidden;
  z-index: -1;
}

.video__container video{
  /* 确保视频居中 */
  position: absolute;
  top: 0;
  align-items: center;
  height: 100%;
  width: 100%;
  object-fit: fill;
  overflow: hidden;
  /*object-fit: fill;*/
}

/* ===================== TEXT & LOGO ==================== */

@keyframes fadein {
  from {
    opacity: 0;
  }
  to{
    opacity: 0.5;
  }
}

@keyframes fadeInUp {
  from{
    opacity: 0;
    transform: translate3d(0, 100%, 0);
  }
  to{
    opacity: 1;
    transform: translate3d(0, 0, 0);
  }
}
.fadeInUp{
  animation-delay: 0.5s;
  animation-name: fadeInUp;
}


.rhodes{
  position: fixed;
  top: 90px;
  left: 0;
  bottom: 0;
  width: 560px;
  height: auto;
  background-image: url("../../assets/rhodes.png");
  background-repeat: no-repeat;
  background-position: 100% 0;
  background-size: auto 110%;
  opacity: 0;
  /*mix-blend-mode: multiply;*/
  animation: fadein 1s ease-in-out 2s forwards;
}

.rhodes__text__container{
  /*border-top: 1px solid white;*/
  box-shadow: inset 0 .3px 0 white;
  cursor: default;
  position: fixed;
  bottom: 3rem;
  left: 0;
  width: 100%;
  /*width: 530px;*/
  height: 80px;
  animation-duration: 2s;
  animation-fill-mode: both;
}

.rhodes__text__1{
  position: absolute;
  left: 3rem;
  color: white;
  font-size: 3.5rem;
  font-family: "Arial Black",serif;

}

.rhodes__text__2{
  display: flex;
  flex-direction: column;
  color: white;
  position: absolute;
  left: 28.5rem;
  top: 1rem;
}
.rhodes__text__21, .rhodes__text__22{
  font-size: 1rem;
}

/* ======== TYPING ANIMATE ========== */

@keyframes typing {
  from {width: 0;}
  to {width: 100%;}
}
.text__typing{
  overflow: hidden;
  white-space: nowrap; /* 保留换行和空格 */
  margin: 0 auto; /* 居中对齐 */
  letter-spacing: .15em; /* 字符间距 */
  animation:
      typing 3.5s steps(40) 1s normal both;
}

</style>