<template>
    <div class="outer">
        <div class="wrapper">
            <div class="bg bg1">
                <div class="line vertical-line"></div>
                <div class="line vertical-line"></div>
                <div class="line vertical-line"></div>
                <div class="line vertical-line"></div>
                <div class="line vertical-line"></div>
                <div class="line vertical-line"></div>
                <div class="line vertical-line"></div>
                <div class="line vertical-line"></div>
                <div class="line vertical-line"></div>
                <div class="line vertical-line"></div>
                <div class="line vertical-line"></div>
                <div class="line vertical-line"></div>
                <div class="line vertical-line"></div>
                <div class="line vertical-line"></div>
                <div class="line vertical-line"></div>
                <div class="line vertical-line"></div>
                <div class="line vertical-line"></div>
                <div class="line vertical-line"></div>
                <div class="line vertical-line"></div>
            </div>
            <div class="bg bg3">
                <div class="line horizontal-line line1"></div>
                <div class="line horizontal-line line2"></div>
                <div class="line horizontal-line line3"></div>
                <div class="line horizontal-line line4"></div>
                <div class="line horizontal-line line5"></div>
                <div class="line horizontal-line line6"></div>
                <div class="line horizontal-line line7"></div>
                <div class="line horizontal-line line8"></div>
                <div class="line horizontal-line line9"></div>
            </div>
        </div>
        <div class="square">
            <ul>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        </div>
    </div>
</template>

<script>

</script>

<style scoped lang="scss">
.outer {
  background: #fff;
  perspective: 105vh;
  overflow: hidden;
}
.wrapper {
  transform-style: preserve-3d;
  transform: rotateX(45deg);
  transform-origin: center top;
  opacity: .5;
  background: white;
  height: 100vh;
  width: 100vw;
  overflow:hidden;
  &::after{
    content:"";
    position:absolute;
    height:100vh;
    width:100vw;
    top:0;
    left:0;
    background:linear-gradient(to top,#ffffff00,#ffffffaa);
  }

  .bg {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    transform-origin: center bottom;
  }
  .line {
    position: absolute;
    top: 0;
    left: 0;
    &::after {
        content: '';
        position: absolute;
        height: 20px;
        width: 4px;
        background: #2171fc;
        border-radius: 100%;
        filter: blur(1px);
        animation: move 8s infinite;
        opacity:0;
      }
  }
  @keyframes move {
  0% {
    transform: translate(-50%, 100vh);
    opacity:0;
  }
  1% {
    transform: translate(-50%, 100vh);
    opacity:1;
  }

  25% {
    transform: translate(-50%, -20vh);
    opacity:1;
  }
  26% {
    transform: translate(-50%, 0vh);
    opacity:0;
  }
  100%{
    opacity:0;
  }
}
  .vertical-line {
    height: 100%;
    width: 1px;
    &:nth-child(1) { transform: translateX(5vw); &::after { animation-delay: 3s } }
    &:nth-child(2) { transform: translateX(10vw); &::after { animation-delay: 5.3s } }
    &:nth-child(3) { transform: translateX(15vw); &::after { animation-delay: 0s } }
    &:nth-child(4) { transform: translateX(20vw); &::after { animation-delay: 1.23s } }
    &:nth-child(5) { transform: translateX(25vw); &::after { animation-delay: 2.3s } }
    &:nth-child(6) { transform: translateX(30vw); &::after { animation-delay: 0.73s } }
    &:nth-child(7) { transform: translateX(35vw); &::after { animation-delay: 3s } }
    &:nth-child(8) { transform: translateX(40vw); &::after { animation-delay: 2.13s } }
    &:nth-child(9) { transform: translateX(45vw); &::after { animation-delay: 1.3s } }
    &:nth-child(10) { transform: translateX(50vw);&::after { animation-delay: 1.2s } }
    &:nth-child(11) { transform: translateX(55vw);&::after { animation-delay: 0.42s } }
    &:nth-child(12) { transform: translateX(60vw);&::after { animation-delay: 5.2s } }
    &:nth-child(13) { transform: translateX(65vw);&::after { animation-delay: 2.7s } }
    &:nth-child(14) { transform: translateX(70vw);&::after { animation-delay: 4.1s } }
    &:nth-child(15) { transform: translateX(75vw);&::after { animation-delay: 5.2s } }
    &:nth-child(16) { transform: translateX(80vw);&::after { animation-delay: 2.3s } }
    &:nth-child(17) { transform: translateX(85vw);&::after { animation-delay: 1.2s } }
    &:nth-child(18) { transform: translateX(90vw); &::after { animation-delay: 3.72s } }
    &:nth-child(19) { transform: translateX(95vw); &::after { animation-delay: 0.82s } }
  }
  .horizontal-line {
    width: 100%;
    height: 1px;
    background: #8eb4f7;
    &:nth-child(1) { animation-delay: 0s; }
    &:nth-child(2) { animation-delay: -2s; }
    &:nth-child(3) { animation-delay: -4s; }
    &:nth-child(4) { animation-delay: -6s; }
    &:nth-child(5) { animation-delay: -8s; }
    &:nth-child(6) { animation-delay: -10s; }
    &:nth-child(7) { animation-delay: -12s; }
    &:nth-child(8) { animation-delay: -14s; }
    &:nth-child(9) { animation-delay: -16s; }
  }
  .bg1 {
    transform: rotate(-.33deg);
  }

  .bg1 .line{
    background: #8eb4f7;
  }
  .bg3 .line{
    animation-timing-function: linear;
    animation-name: moveHorizontal;
    animation-duration: 18s;
    animation-iteration-count: infinite;
    animation-direction: reverse;
  }
}
@keyframes moveHorizontal {
  0% {
    transform: translateY(100vh);
  }

  100% {
    transform: translateY(0);
  }
}

ul li{
    position: absolute;
    background: linear-gradient(45deg,#00b3f8,#006cfc);
    filter:blur(3px);
    width: 30px;
    height: 30px;
    list-style: none;
    opacity: 0;
}
.square li{
    /* 执行动画：动画名 时长 线性的 无限次播放 */
    animation: square 10s linear infinite;
}
.square li:nth-child(1){
    top: 92.5vh;
    left: 0vw;
    /* 设置动画延迟时间 */
    animation-delay: 2s;
}
.square li:nth-child(2){
    top: 95vh;
    left: 97vw;
    /* 设置动画延迟时间 */
    animation-delay: 4s;
}
.square li:nth-child(3){
    top: 15vh;
    left: 70vw;
    /* 设置动画延迟时间 */
    animation-delay: 6s;
}
.square li:nth-child(4){
    top: 0vh;
    left: 40vw;
    /* 设置动画延迟时间 */
    animation-delay: 8s;
}

/* 定义动画 */
@keyframes square {
    0%{
        transform: scale(0);
        opacity: 0;
    }
    50%{
        transform: scale(2.5);
        opacity: 0.8;
    }
    100%{
        transform: scale(5);
        opacity: 0;
    }
}
</style>