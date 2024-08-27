<template>
  <!--  <div class="main__container">-->
    <div class="container">
      <el-carousel height="527.5px" :interval="4000" arrow="always" class="carousel" @change="handleCarouselChange">
        <el-carousel-item v-for="item in images" :key="item.id">
          <img style="object-fit: scale-down; width: 100%;height: 100%" :src="item.itemID" alt="Carousel image" @click="openLink($event, item.link)">
        </el-carousel-item>
      </el-carousel>

      <div class="announce">
        <div class="announce__body">
      <!-- ======================= Announcement Text ========================= -->
        <div class="carousel__announce" style="display: flex">
          <div class="new_act" >
            <span style="color: black; font-size: small" >活动</span>
            <div class="el-icon-d-arrow-right" style="color: black; margin-left: 20px">
            </div>
          </div>

          <div class="announcement" >
            <span style="color: white; font-size: small">公告</span>
          </div>

          <div class="news" >
            <span style="color: white; font-size: small">新闻</span>
          </div>

        </div>

          <transition name="slide" mode="out-in">
          <div class="announce__box">

                                  <!-- ========== target=_blank ==>新打开href指定网页  ========  -->


          <a class="announce__text" v-for="(record, index) in records" :key="index" :href="record.textUrl" target="_blank">
            <div class="announce__kinds">{{ record.textType }}</div>
            <div class="kinds__text">
              <div class="kinds__time">{{ record.textTime }}</div>
              <div class="kinds__name">{{ record.textAbout }}</div>
            </div>
          </a>

          </div>
          </transition>

<!--          <transition name="slide" mode="out-in">-->

<!--          <div class="announce__box1" >-->

<!--                                  &lt;!&ndash; ========== target=_blank ==>新打开href指定网页  ========  &ndash;&gt;-->


<!--          <a class="announce__text" v-for="(record, index) in records" :key="index" :href="record.textUrl" target="_blank">-->
<!--            <div class="announce__kinds">{{ record.textType }}</div>-->
<!--            <div class="kinds__text">-->
<!--&lt;!&ndash;              <div class="kinds__time">{{ record.textTime }}</div>&ndash;&gt;-->
<!--              <div class="kinds__name">{{ record.textAbout }}</div>-->
<!--            </div>-->
<!--          </a>-->

<!--          </div>-->
<!--          </transition>-->

<!--          <transition name="slide" mode="out-in">-->

<!--            <div class="announce__box2">-->

<!--                                  &lt;!&ndash; ========== target=_blank ==>新打开href指定网页  ========  &ndash;&gt;-->


<!--          <a class="announce__text" v-for="(record, index) in records" :key="index" :href="record.textUrl" target="_blank">-->
<!--            <div class="announce__kinds">{{ record.textType }}</div>-->
<!--            <div class="kinds__text">-->
<!--              <div class="kinds__time">{{ record.textTime }}</div>-->
<!--&lt;!&ndash;              <div class="kinds__name">{{ record.textAbout }}</div>&ndash;&gt;-->
<!--            </div>-->
<!--          </a>-->

<!--          </div>-->
<!--          </transition>-->


        </div>


<!--        <div class="carousel__text">-->
<!--          <div class="text__time"><a style="color: white">2024 // 05 // 01</a></div>-->
<!--          <div class="active__name"><a style="color: white">S i d e S t o r y 「 孤星 」 活 动 复 刻</a></div>-->
<!--        </div>-->
        <div class="carousel__text" ref="carouselText">
          <div class="text__time"><a style="color: white">{{currentImage.text.time}}</a></div>
          <div class="active__name"><a style="color: white">{{currentImage.text.name}}</a></div>
        </div>
      </div>
  </div>
</template>

<script>
export default {
  name: "exp-z",
  data(){
    return{
      currentImage: {},
      screenWidth: 0,
      currentView: 'active',
      items: [],
      records:[],


      // ====================================== Image Text Set ==================================
      images: [
        // {id: 0, itemID:require("@/assets/10001.png")},
        {id: 0, itemID:require("@/assets/10002.png"), link:'https://www.baidu.com/',
          text: {time: '2024 // 06 // 01', name:'全新生息演算主题'}},
        {id: 1, itemID:require("@/assets/ak_active.jpg"), link: 'https://www.bilibili.com/opus/935011068951920690?spm_id_from=333.999.0.0',
          text: {time: '2024 // 05 // 01', name:'S i d e S t o r y 「 孤星 」 活 动 复 刻'}},
      ],

    }
  },

  created() {
    // ===================== Refer Image to Text Created =============================
    this.currentImage = this.images[0]
    this.getInfo()
  },


  methods:{
    getInfo(){
      this.request.get("/manager/information/page",{
        params:{
          pageNum: 1,
          pageSize: 3,
          textType: this.textType,
        }
      }).then(res => {
        this.records = res.records

      })
    },

    // ========================= Image Link ============================
    openLink(event, url){
      event.preventDefault();
      window.open(url, '_blank');
    },

    // ========================= Image Text Control ============================

    handleCarouselChange(index){
      // this.currentImage = this.images[index];
      this.$refs.carouselText.classList.add('slide-out');
      // ======================= TimeOut Set =======================================
      setTimeout(() => {
        this.currentImage = this.images[index];
        this.$refs.carouselText.classList.remove('slide-out');
        this.$refs.carouselText.classList.add('slide-in');
      }, 500);
    },

    // ======================== Move Set ========================================

    // slideInText() {
    //   this.$refs.carouselText.classList.remove('slide-out');
    //   this.$refs.carouselText.classList.add('slide-in');
    // },
    slideOutText() {
      this.$refs.carouselText.classList.remove('slide-in');
      this.$refs.carouselText.classList.add('slide-out');
    }
  },

}
</script>

<style scoped>
/* ========================= Container ========================== */
.main__container{
  height: 100%;
  width: 100%;
  overflow: hidden;
}
.container{
  width: 100%;
  height: 100%;
  background-image: url("~@/assets/10003.jpg");
  z-index: -1;
}
/* ========================= Carousel ======================== */
.carousel{
  position: absolute;
  top: 90px;
  left: 25%;
  width: 900px;
  border-top: .3px solid rgb(94, 94, 94);
}
.el-carousel__item img{
  cursor: pointer;
}
/* ======================== Announcement ======================== */
.announce{
  position: absolute;
  left: 0;
  top: 90px;
  width: 384px;
  height: 528px;
  border-top: .3px solid rgb(94, 94, 94);
}

.announce__kinds{
  color: rgb(25, 209, 255);
  margin-right: auto;
}

.kinds__text{
  width: 230px;
  height: 50px;
  color: #d2d2d2;
  margin-left: auto;
  font-size: small;
}

.kinds__time{
  height: 15px;
}
.kinds__name{
  /*height: 35px;*/
  max-height: 35px;
  font-weight: 500;
  overflow: hidden;
  margin-top: 2px;
}

.carousel__announce{

  position: absolute;
  top: 30px;
  width: 250px;
  height: 18px;
}

.announce__body{
  position: relative;
  left: 30px;
  width: 100%;
  height: 300px;
}

.carousel__text{
  position: relative;
  height: 230px;
  top: 60px;
  /*width: 380px;*/
  width: 95%;
  left: 30px;
  transition: transform 0.5s ease-in-out;
  cursor: default;

}

.announce__text{
  height: 70px;
  width: 300px;
  display: flex;
  cursor: pointer;
  border-bottom: 1px solid hsla(0, 0%, 100%, 0.3);
  align-items: center;
  text-decoration: none;
}

.announce__box,
.announce__box1,
.announce__box2{
  position: absolute;
  top: 50px;
  width: 100%;
  height: 240px;

}

.carousel__text.slide-in {
  transform: translateX(0);
}

.carousel__text.slide-out {
  transform: translateX(-100%);
}

.text__time{
  padding: 5px;
  font-size: large;
}
.active__name{
  padding: 5px;
  font-size: 30px;
}

.news, .announcement, .new_act{
  /*margin-right: 3rem;*/
  margin-right: auto;
  align-items: center;
  display: flex;
  cursor: pointer;
  background-color: rgb(25, 209, 255);
}


.el-carousel__item:nth-child(2n) {
  /*background-color: #99a9bf;*/
  background-color: #ffffff;
}

.el-carousel__item:nth-child(2n+1) {
  /*background-color: #d3dce6;*/
  background-color: #ffffff;
}

/*.el-carousel__item h3 {*/
/*  color: #475669;*/
/*  font-size: 18px;*/
/*  opacity: 0.75;*/
/*  line-height: 100%;*/
/*  margin: 0;*/
/*}*/

/* =============== OTHER CSS ================= */
/* 新页面开始时从完全透明和屏幕右侧进入 */
.slide-enter-active,
.slide-leave-active{
transition: opacity 1s;
}
.slide-enter,
.slide-leave-to{
  opacity: 0;
}

</style>