const { createApp } = Vue;

const app = createApp({
    data() {
        return {
            transactions: [],
            account: {},
        };
    },
    created() {
        const search = location.search;
        const params = new URLSearchParams(search);
        this.id = params.get('id');
        this.loadData();
    },
    methods: {
        loadData() {
            axios.get("/api/accounts/" + this.id)
                .then(response => {
                    this.account = response.data
                    this.transactions = response.data.transactions
                })
                .catch(error => {
                    if (error.response && error.response.status === 404) {
                        console.log("No se encontró la cuenta o no hay transacciones.");
                    } else {
                        console.error(error);
                    }
                });
        },
        formatBudget(balance) {
            if (balance !== undefined && balance !== null) {
                return balance.toLocaleString("en-US", {
                    style: "currency",
                    currency: "ARS",
                    minimumFractionDigits: 0,
                });
            }
            return '';
        },
        formatFechaYHora(fechaYHora) {
            if (fechaYHora) {
                const fechaFormateada = new Date(fechaYHora);
                const opciones = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit', second: '2-digit' };
                return fechaFormateada.toLocaleString('en-US', opciones);
            }
            return '';
        },
    },
}).mount('#app');
