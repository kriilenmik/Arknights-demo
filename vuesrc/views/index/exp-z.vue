<template>
  <!--  <div class="main__container">-->
    <div class="container">
      <el-carousel height="527.5px" :interval="4000" arrow="always" class="carousel" @change="handleCarouselChange">
        <el-carousel-item v-for="item in images" :key="item.id">
          <img style="object-fit: scale-down; width: 100%;height: 100%" :src=item.itemID alt="Carousel image" @click="openLink($event, item.link)">
        </el-carousel-item>
      </el-carousel>

      <div class="announce">
        <div class="announce__body">
      <!-- ======================= Announcement Text ========================= -->
        <div class="carousel__announce" style="display: flex">
<!--          <div class="new_act" @click="activeContainer = 'A'">-->
<!--          <div class="new_act" @click="activeContainer = 'A'">-->
<!--            <span style="color: white; font-size: small">活动</span>-->
<!--            <div class=el-icon-d-arrow-right style="margin-left: 10px">-->
<!--            </div>-->
<!--          </div>-->
<!--          <div class="announcement" @click="activeContainer = 'B'">-->
<!--            <span style="color: white; font-size: small">公告</span>-->
<!--          </div>-->
<!--          <div class="news" @click="activeContainer = 'C'">-->
<!--            <span style="color: white; font-size: small">新闻</span>-->
<!--          </div>-->


          <!----------------------------------- -->
          <div class="tab new_act"
               :class="{ active: activeContainer === 'A' }"
               @click="activeContainer = 'A'">
            <span style="font-size: small">活动</span>
            <div v-if="activeContainer === 'A'" class="el-icon-d-arrow-right"></div>
          </div>
          <div class="tab announcement"
               :class="{ active: activeContainer === 'B' }"
               @click="activeContainer = 'B'">
            <span style="font-size: small">公告</span>
            <div v-if="activeContainer === 'B'" class="el-icon-d-arrow-right"></div>
          </div>
          <div class="tab news"
               :class="{ active: activeContainer === 'C' }"
               @click="activeContainer = 'C'">
            <span style="font-size: small">新闻</span>
            <div v-if="activeContainer === 'C'" class="el-icon-d-arrow-right"></div>
          </div>

          <!--- ============================= -->


        </div>


          <div class="announce__box">
            <transition-group name="fade" >

            <div v-show="activeContainer === 'A'" key="A" style="position: absolute; width: 100%">
            <a class="announce__text" v-for="(record, index) in records1" :key="index" :href="record.textUrl" target="_blank">
              <div class="announce__kinds">{{ record.textType }}</div>
              <div class="kinds__text">
                <div class="kinds__time">{{ record.textTime }}</div>
                <div class="kinds__name">{{ record.textAbout }}</div>
              </div>
            </a>
            </div>

            <div v-show="activeContainer === 'B'" key="B" style="position: absolute; width: 100%;">

              <a class="announce__text" v-for="(record, index) in records2" :key="index" :href="record.textUrl" target="_blank">
                <div class="announce__kinds">{{ record.textType }}</div>
                <div class="kinds__text">
                  <div class="kinds__time">{{ record.textTime }}</div>
                  <div class="kinds__name">{{ record.textAbout }}</div>
                </div>
              </a>
            </div>

              <div v-show="activeContainer === 'C'" key="C" style="position: absolute; width: 100%;">

                <a class="announce__text" v-for="(record, index) in records3" :key="index" :href="record.textUrl" target="_blank">
                  <div class="announce__kinds">{{ record.textType }}</div>
                  <div class="kinds__text">
                    <div class="kinds__time">{{ record.textTime }}</div>
                    <div class="kinds__name">{{ record.textAbout }}</div>
                  </div>
                </a>
              </div>

            </transition-group>

          </div>


        </div>


        <div class="carousel__text" ref="carouselText">
          <div class="text__time"><a style="color: white">{{currentImage.text.times}}</a></div>
          <div class="active__name"><a style="color: white">{{ currentImage.text.name }}</a></div>
        </div>

      </div>
  </div>
</template>

<script>
export default {
  name: "exp-z",
  data(){
    return{
      currentImage: {
        text:{
          times: '默认空值',
          name: '默认空值',
        }
      },
      screenWidth: 0,
      records1:[],
      records2:[],
      records3:[],
      activeContainer: 'A',
      textType: '',
      pageNum: 1,
      pageSize: 5,
      records: "",
      id: null,
      itemID: '',
      link: '',
      images: [],


      // ====================================== Image Text Set ==================================
      // images: [{"id": 0, "itemID":"http://localhost:9090/manager/pic/download/1719192131796_ak_active.jpg", "link":"https://www.baidu.com/",
      //   "text": {"time": "2024 // 06 // 01", "name":"全新生息演算主题"}},
      //   {id: 12, itemID:require("@/assets/ak_active.jpg"), link: 'https://www.bilibili.com/opus/935011068951920690?spm_id_from=333.999.0.0',
      //     text: {time: '2024 // 05 // 01', name:'S i d e S t o r y 「 孤星 」 活 动 复 刻'}},],        // {id: 0, itemID:require("@/assets/10001.png")},

    }
  },
  created() {
    // ===================== Refer Image to Text Created =============================
    // this.currentImage = this.images[0]
    this.getInfo1()
    this.getInfo2()
    this.getInfo3()
    this.getImage()
  },


  methods:{

    getImage(){
      this.request.get("/manager/pic/findAll").then(res =>{
        this.images = res.map(item =>({
          id: item.id,
          itemID: item.imageUrl,
          link: item.urlText,
          text: {
            times: item.imageTime,
            name: item.imageText,
          }
        }));
        console.log(this.images)
        if (this.images.length > 0) {
          this.$nextTick(()=>{
            this.currentImage = this.images[0];
          })

        }
      })

    },

    getInfo1(){
      this.request.get("/manager/information/pageFind",{
        params:{
          textType: '活动',
        }
      }).then(res => {
        this.records1 = res;
      })
    },
    getInfo2(){
      this.request.get("/manager/information/pageFind",{
        params:{
          textType: '公告',
        }
      }).then(res => {
        this.records2 = res;
      })
    },
    getInfo3(){
      this.request.get("/manager/information/pageFind",{
        params:{
          textType: '新闻',
        }
      }).then(res => {
        this.records3 = res;
      })
    },

    // ========================= Image Link ============================
    openLink(event, url){
      event.preventDefault();
      window.open(url, '_blank');
    },

    // ========================= Image Text Control ============================

    handleCarouselChange(index){
      this.slideOutText();
      // this.currentImage = this.images[index];
      // this.$refs.carouselText.classList.add('slide-out');
      // ======================= TimeOut Set =======================================
      setTimeout(() => {
        this.currentImage = this.images[index];
        this.slideInText();
        // this.$refs.carouselText.classList.remove('slide-out');
        // this.$refs.carouselText.classList.add('slide-in');
      }, 500);
    },

    // ======================== Move Set ========================================


    slideOutText() {
      // this.$refs.carouselText.classList.remove('slide-in');
      this.$refs.carouselText.classList.add('slide-out');
    },
    slideInText() {
      this.$refs.carouselText.classList.remove('slide-out');
      this.$refs.carouselText.classList.add('slide-in');
    },
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

.announce__box{
  position: relative;
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
  /*background-color: rgb(25, 209, 255);*/
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

.fade-enter-active, .fade-leave-active {
  transition: all .5s;
}
.fade-enter, .fade-leave-to {
  opacity: 0;
  transform: translateX(30px);
}
.fade-leave, .fade-enter-to{
  opacity: 1;

}
.arrow-active{
  color: black;
  margin-left: 10px;
}

.tab {
  color: white;
}

.tab.active {
  background-color: rgb(25, 209, 255);
  color: black;
}

.el-icon-d-arrow-right {
  color: black;
  margin-left: 10px;
  /* 这里添加你的箭头样式 */
}

</style>