<template>
  <div>
    <header class="header" id="header">
      <nav class="nav container">
        <div class="nav_menu" id="nav-menu">
          <ul class="nav__list">

            <li>
              <a>
                <router-link to="/test/a" class="router__link__btn">Index</router-link>
              </a>
            </li>

            <li>
              <a>
                <router-link to="/test/b" class="router__link__btn">About us</router-link>
              </a>
            </li>

          </ul>
          <div class="nav__close" id="nav-close" @click="Close_menu">
            <i class="el-icon-close"></i>
          </div>
        </div>

      </nav>
    </header>
  </div>
</template>

<script>
export default {
  name: "index1"
}
</script>

<style scoped>

/* ==================================== HEADER & NV ======================================*/
.header{
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  background-color: rgba(128, 128, 128, 0.5) ;
  box-shadow: 0 2px 16px hsla(230, 75%, 32%, 0.15);
  z-index: var(--z-fixed);
}
.nav{
  height: var(--header-height);
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.nav__list{
  list-style: none;         /*去除浏览器链接默认圆点样式*/
  display: flex;
  flex-direction: column;
  row-gap: 2.5rem;
  text-align: center;
}

/* ============================== Navigation for mobile devices  =============================*/
@media screen and (max-width: 1023px){
  .nav__menu{
    position: fixed;
    right: -100%;
    top: 0;
    /*background-color: var(--body-color);*/
    background-color: rgba(255, 255, 255, 0.5);
    box-shadow: 0 8px 16px hsla(230, 75%, 32%, 0.15);
    width: 100%;
    padding-block: 4.5rem 4rem;
    transition: right 0.7s;
  }
}
.nav__menu.active{
  right: 0;
}
.nav__close{
  position: absolute;
  top: 1.15rem;
  right: 1.5rem;
}
</style>