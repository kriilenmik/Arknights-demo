<template>
  <div class="main">
    <img src="../assets/previ.png" alt="image" class="main__bg">

  </div>
</template>




<script>
export default {
  name: "Imge"
}
</script>


<style scoped>

.main__bg{
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  object-position: center;
  z-index: -1;
}
.main{
  position: relative;
  height: 100vh;
}



</style>