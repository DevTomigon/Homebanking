const { createApp } = Vue;

const app = createApp({
  data() {
    return {
      cardType: '',
      cardColor: '',
      cards: [],
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
    createCard() {
      axios.post("/api/clients/current/cards?type=" + this.cardType.toUpperCase() + "&color=" + this.cardColor.toUpperCase())
        .then(response => {
          console.log('Tarjeta creada con éxito:', response.data);
          this.loadData();
          Swal.fire({
            icon: 'success',
            title: 'Tarjeta creada con éxito',
            showConfirmButton: false,
            timer: 1500,
          });
        })
        .catch(error => {
          console.error('Error al crear la tarjeta:', error);
          Swal.fire({
            icon: 'error',
            title: 'Error al crear la tarjeta',
            text: 'Por favor, inténtelo de nuevo más tarde',
          });
        });
    },
  },
});

app.mount('#app');
