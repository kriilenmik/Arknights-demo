<template>
  <div>
    <!-- ==========  HEADER   =================== -->
    <header class="header" id="header">
      <nav class="nav_container">
        <a href="#" class="nav__logo">Logo</a>

        <div class="nav__menu" id="nav-menu">
          <ul class="nav__list">
            <li class="nav__item">
              <a href="#" class="nav__link">Home</a>
            </li>

            <li class="nav__item">
              <a href="#" class="nav__link">About Us</a>
            </li>

            <li class="nav__item">
              <a href="#" class="nav__link">Services</a>
            </li>

            <li class="nav__item">
              <a href="#" class="nav__link">Featured</a>
            </li>

            <li class="nav__item">
              <a href="#" class="nav__link">Contact Us</a>
            </li>
          </ul>

          <!-- ===== CLOSE_Button ========  -->
          <div class="nav__close" id="nav-close">
            <i class="el-icon-close"></i>
          </div>
        </div>


        <div class="nav__actions">
          <!-- =========== SEARCH_Button ========== -->
          <i class="el-icon-search nav__search"></i>

          <!-- =========== LOGIN_User_Button ========== -->
          <i class="el-icon-user nav__login"></i>

          <!-- =========== TOGGLE_Button ========== -->
          <div class="nav__toggle" id="nav-toggle">
            <i class="el-icon-menu"></i>
          </div>
        </div>
      </nav>
    </header>

    <main class="main">
      <img src="src/assets/previ.png" alt="image" class="main__bg" @load="handleImageLoaded">
    </main>

  </div>
</template>

<script>
export default {
  name: "test"
}
</script>

<style scoped>

*{
  box-sizing: border-box;
  padding: 0;
  margin: 0;
}
html{
  scroll-behavior: smooth;
}

/* ================ HEADER & NV ===================*/
.header{
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  background-color: var(--body-color);
  box-shadow: 0 2px 16px hsla(230, 75%, 32%, 0.15);
  z-index: 2000;
}


.main{
  height: 100vh;
  position: relative;
}
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
  overflow: hidden;
}
</style>