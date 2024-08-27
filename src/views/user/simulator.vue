<template>
  <div class="app">
    <h1>明日方舟抽卡模拟器</h1>
    <button @click="drawCard">抽卡</button>
    <div v-for="card in cards" :key="card.id" class="card">
      < img :src="card.image" alt="Card Image" class="card-image">
      <h3>{{ card.name }}</h3>
      <p>Rarity: {{ card.rarity }}</p >
    </div>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      cards: [],
      rarityProbabilities: {
        3: 0.7, // 3星卡牌的概率为70%
        4: 0.2, // 4星卡牌的概率为20%
        5: 0.1 // 5星卡牌的概率为10%
      },
      cardData: [
        {
          id: 1,
          name: 'Card 1',
          rarity: 3,
          image: 'card1.jpg'
        },
        {
          id: 2,
          name: 'Card 2',
          rarity: 4,
          image: 'card2.jpg'
        },
        {
          id: 3,
          name: 'Card 3',
          rarity: 5,
          image: 'card3.jpg'
        },
// 添加更多的卡牌数据
      ]
    };
  },
  methods: {
    drawCard() {
      const rarity = this.getRarity();
      const card = this.generateCard(rarity);
      this.cards.push(card);
    },
    getRarity() {
      const random = Math.random();
      let cumulativeProbability = 0;

      for (const rarity in this.rarityProbabilities) {
        cumulativeProbability += this.rarityProbabilities[rarity];

        if (random <= cumulativeProbability) {
          return parseInt(rarity);
        }
      }

      return parseInt(Object.keys(this.rarityProbabilities)[0]);
    },
    generateCard(rarity) {
      const filteredCards = this.cardData.filter(card => card.rarity === rarity);
      const randomIndex = Math.floor(Math.random() * filteredCards.length);

      return filteredCards[randomIndex];
    }
  }
}
</script>

<style>
.app {
  text-align: center;
}

.card {
  margin: 20px;
  padding: 10px;
  border: 1px solid gray;
  display: inline-block;
}

.card-image {
  width: 200px;
  height: 200px;
}
</style>