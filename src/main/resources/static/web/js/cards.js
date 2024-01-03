const { createApp } = Vue;

const app = createApp({
  data() {
    return {
      cards: [],
      credit: [],
      debit: [],
      client: {},
    };
  },
  created() {
    const search = location.search;
    const params = new URLSearchParams(search);
    this.clientId = params.get('id');
    this.loadData();
  },
  methods: {
    loadData() {
      axios.get(`/api/clients/current`)
        .then(response => {
          this.cards = response.data.cards;
          console.log(response);
          console.log(this.cards);
          this.client = response.data;
          
          // Filtering credit and debit cards separately
          this.credit = this.cards.filter(card => card.cardType === "CREDIT");
          this.debit = this.cards.filter(card => card.cardType === "DEBIT");
        })
        .catch(error => {
          if (error.response && error.response.status === 404) {
            console.log("No se encontró la cuenta.");
          } else {
            console.error(error);
          }
        });
    },
    goToCreateCardsPage() {
      window.location.href = '/web/create-cards.html';
    },
  },
}).mount('#app');
