<template>

  <div>
    <div class="container">
      <canvas ref="akCanvas" id="akCanvas"></canvas>
    </div>
    <button style="color: red; position: fixed; height: 10rem; width: 10rem; right: 20px; top: 5rem" @click="handleClick">1234</button>
  </div>

</template>

<script>

import _ from 'lodash';
import {initDameDaneParticle} from '../../utils/index'
import {arknight, island, longmen, penguin, rhine, reunion} from '../../utils/index'
export default {
  name: "canvas1",
  data(){
    return{
      DameDaneParticle: null,

    };
  },
  methods:{
    handleClick(){
      this.DameDaneParticle.ChangeImg('/image/arknight.png', {w: 600});
    },
    arknight,
    island,
    longmen,
    penguin,
    rhine,
    reunion,
    },
  created() {
  },
  mounted() {
    this.DameDaneParticle = initDameDaneParticle(this.$refs.akCanvas);
    console.log(this.DameDaneParticle)

  },
  beforeDestroy() {
    this.DameDaneParticle.PreDestory();
  }
}
</script>

<style scoped>
.container {
  width: 100%;
  height: 100%;
  background-color: #000;
  overflow: hidden;
}
#akCanvas {
  /* pointer-events: none; */;
  cursor: url("../../utils/image/mouse.png") 0 0, pointer;
}
</style>