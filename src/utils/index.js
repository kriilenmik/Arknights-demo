import {DameDaneParticle} from "./canvasClass";


export function initDameDaneParticle(canvas){
  return new DameDaneParticle(
      canvas,
      {
        src: '/image/penguin.png',
        renderX: 30,
        renderY: 100,
        w: 300,
        h: 360,
        size: 1.2,
        spacing: 1.2,
        validColor: {
          min: 300,
          max: 765,
          invert: false
        },
        effectParticleMode: 'repulsion',
        Thickness: 25,
      }
  );
}



export function arknight() {
  DameDaneParticleDemo.ChangeImg('/image/arknight.png', { w: 600 })
}
// export function arknight() {
//   this.DameDaneParticle.ChangeImg('/image/arknight.png', { w: 600 })
// }


export function island() {
  DameDaneParticleDemo.ChangeImg('/image/island.png', { w: 400 })
}

export function longmen() {
  DameDaneParticleDemo.ChangeImg('/image/longmen.png', { w: 450 })
}

export function penguin() {
  DameDaneParticleDemo.ChangeImg('/image/penguin.png', { w: 300 })
}

export function rhine() {
  DameDaneParticleDemo.ChangeImg('/image/rhine.png', { w: 400 })
}

export function reunion() {
  DameDaneParticleDemo.ChangeImg('/image/reunion.jpg', { w: 300 })
}
export function one() {
  DameDaneParticleDemo.ChangeImg('/image/1.png', { w: 300 })
}


