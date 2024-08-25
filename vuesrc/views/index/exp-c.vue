<template>
  <div class="main__container">


    <div class="info__container">

      <div class="info__text text__hover" style="display: flex" @click="handleClick1">
        <div class="info__text__1">源石</div>
        <div class="info__text__12">ORIGINIUMS</div>
      </div>
      <div class="info__text text__hover" style="display: flex" @click="handleClick2">
        <div class="info__text__1 ">源石技艺</div>
        <div class="info__text__12">ORIGINIUMS ARTS</div>
      </div>
      <div class="info__text text__hover" style="display: flex" @click="handleClick3">
        <div class="info__text__1">感染者</div>
        <div class="info__text__12">INFECTED</div>
      </div>
      <div class="info__text text__hover" style="display: flex" @click="handleClick4">
        <div class="info__text__1">整合运动</div>
        <div class="info__text__12">REUNION</div>
      </div>
      <div class="info__text text__hover" style="display: flex" @click="handleClick5">
        <div class="info__text__1">罗德岛</div>
        <div class="info__text__12">RHODES ISLAND</div>
      </div>

    </div>

    <div class="container__canvas">
      <canvas ref="akCanvas" id="akCanvas" :width="canvasWidth" :height="canvasHeight"></canvas>
    </div>

    <div class="text__container">
      <div class="text__main" v-if="currentText === 'text1'">
        大地被起因不明的天灾四处肆虐，经由天灾席卷过的土地上出现了大量的神秘矿物——“源石”。
        依赖于技术的进步，源石蕴含的能量投入工业后使得文明顺利迈入现代，与此同时，源石本身也催生出“感染者”的存在。
      </div>
      <div class="text__main" v-if="currentText === 'text2'">
        源石被发现之后，人们发掘出一种通过它来施放一系列令物质改变原有性状的技术，这种技术被称为源石技艺，常被俗称为“法术”。
        源石技艺所运用的能源，一般被认为来自于源石本身。而人是否能施放法术，以及所能施放法术的形式、强度、效果等，通常受到先天具备的素质、后天对源石技艺的学习能力这两方面因素的制约。
      </div>
      <div class="text__main" v-if="currentText === 'text3'">
        被源石所感染的人。理论致死率100% ，死亡时存在扩散传染性以及潜在危险能力，
        是各国隔离驱逐的目标。长久以来，没人告诉他们该如何渡过余生，如今伴随着一位颠覆者的出现，越来越多的感染者被纳入一场名为“整合运动”的反抗浪潮。
      </div>
      <div class="text__main" v-if="currentText === 'text4'">
        无种族立场，极端排外的感染者组织。他们宣称“感染者应对自己的身份感到骄傲，积极去获取并使用属于自己的力量”。
        试图用最原始的手段去争夺世界的公正。以某座被摧毁的伟大城市为开端，医疗机构“罗德岛”的突然介入，令整个事态向着未知发展。
      </div>
      <div class="text__main" v-if="currentText === 'text5'">
        罗德岛制药公司作为感染者问题专家，聘用感染者，深入危险地区，通过种种手段，已经成功解决了数起感染者引发的事件。
        而今，他们将面对史无前例的感染者暴乱。在各个势力间游走，发掘不为人知的内幕，抵挡感染者的疯狂进攻，你的决策将决定罗德岛的方向。
      </div>

    </div>

    <div class="wrapper">
      <div class="canvas__container">
      <canvas ref="canvas"></canvas>
      </div>
    </div>
  </div>
</template>


<script>
import _ from 'lodash';
import {initDameDaneParticle} from '../../utils/index'
import {arknight, island, longmen, penguin, rhine, reunion} from '../../utils/index'
export default {
  data(){
    return{
      DameDaneParticle: null,
      canvasWidth: 512,
      canvasHeight: 320,
      currentText: 'text1',
    };
  },
  mounted() {
    this.drawSquare();
    this.DameDaneParticle = initDameDaneParticle(this.$refs.akCanvas);
    console.log(this.DameDaneParticle);
  },
  methods: {
    handleClick1(){
      this.DameDaneParticle.ChangeImg('/image/penguin.png', {w: 300});
      this.currentText = 'text1'
    },
    handleClick2(){
      this.DameDaneParticle.ChangeImg('/image/longmen.png', {w: 350});
      this.currentText = 'text2'
    },
    handleClick3(){
      this.DameDaneParticle.ChangeImg('/image/rhine.png', {w: 400});
      this.currentText = 'text3'
    },
    handleClick4(){
      this.DameDaneParticle.ChangeImg('/image/reunion.jpg', {w: 300});
      this.currentText = 'text4'
    },
    handleClick5(){
      this.DameDaneParticle.ChangeImg('/image/island.png', {w: 400});
      this.currentText = 'text5'
    },
    arknight,
    island,
    longmen,
    penguin,
    rhine,
    reunion,
    drawSquare() {
      const canvas = this.$refs.canvas;
      const ctx = canvas.getContext('2d');

      // 设置画布的宽高
      canvas.width = window.innerWidth;
      canvas.height = window.innerHeight;

      // 正方形的大小
      const squareSize = 160.5;

      // 线条颜色
      ctx.strokeStyle = 'rgb(27, 27, 27)';

      ctx.lineWidth = 2;

      // 计算行和列的数量
      const rows = Math.ceil(canvas.height / squareSize);
      const cols = Math.ceil(canvas.width / squareSize);

      // 绘制正方形
      for (let i = 0; i < rows; i++) {
        for (let j = 0; j < cols; j++) {
          // 绘制正方形
          ctx.strokeRect(j * squareSize, i * squareSize, squareSize, squareSize);

          // 绘制对角线
          ctx.beginPath();
          ctx.moveTo(j * squareSize, i * squareSize);
          ctx.lineTo((j + 1) * squareSize, (i + 1) * squareSize);
          ctx.moveTo((j + 1) * squareSize, i * squareSize);
          ctx.lineTo(j * squareSize, (i + 1) * squareSize);
          ctx.stroke();
        }
      }
    }
  },
  beforeDestroy() {
    this.DameDaneParticle.PreDestory();
  },
}
</script>


<style scoped>
/* =========== CONTAINER ================ */
.main__container{
  height: 100%;
  width: 100%;
  overflow: hidden;
}

.container__canvas{
  position: fixed;
  width: 32rem;
  height: 30em;
  right: 25em;
  top: 50px;
  overflow: hidden;
  /*z-index: -1;*/
}

.info__container{
  position: absolute;
  left: 6rem;
  top: 9rem;
  width: 26rem;
  height: 30rem;
  /*width: 540px;*/
  /*height: 490px;*/
}
.text__container{
  position: absolute;
  right: 2rem;
  bottom: 10rem;
  width: 30rem;
  height: 26rem;
}
.canvas__container{
  height: 100%;
  position: fixed;
  width: calc(100% - 252px);
  z-index: -3;

  overflow: hidden;

}


/* =================== INFO TEXT ================ */
.info__text{
  position: relative;
  height: 80px;
  line-height: 1;
  align-items: flex-end;
  border-bottom: 1px solid white;
  padding-bottom: .75rem;
  opacity: 0;
  animation: slideText 0.75s ease-out forwards;
  cursor: pointer;
}
.info__text__1, .info__text__12{
  color: #ababab;
  bottom: 1rem;
  transition: color 0.3s, transform 0.3s;
}
.info__text__1{
  font-size: x-large;
}
.info__text__12{
  margin-left: 1rem;
  font-size: large;
}

@keyframes slideText {
  0%{
    opacity: 0;
    transform: translateX(-100%);
  }
  100%{
    opacity: 1;
    transform: translateX(0);
  }
}

.info__text:nth-child(1){
  animation-delay: 0.45s;
}
.info__text:nth-child(2){
  animation-delay: 0.6s;
}
.info__text:nth-child(3){
  animation-delay: 0.75s;
}
.info__text:nth-child(4){
  animation-delay: 0.9s;
}
.info__text:nth-child(5){
  animation-delay: 1.05s;
}
/* ========== TEXT HOVER ================== */
.text__hover{
  transition: color 0.3s, transform 0.3s;
}
@media (any-hover: hover) {
  .text__hover:hover .info__text__1, .text__hover:hover .info__text__12{
    transform: translateX(15px);
    color: #fff;
  }
}


/* =============== TEXT INTRO ================ */
.text__main{
  color: white;
  writing-mode: vertical-rl;
}



/* =============== OTHER CSS ============== */
.wrapper{
  position: absolute;
  margin: 0;
  height: 100vh;
  width: 100vw;
  z-index: -2;
  background-image: url("../../assets/10003.jpg");
  background-color: black;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}

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
</style>