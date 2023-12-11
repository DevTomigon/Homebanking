// account.js
const app = Vue.createApp({
    data() {
        return {
            loading: true,
            firstName: "",
            lastName: "",
            accounts: [],
            creationDate: "",
        };
    },
    created() {
        this.loadData();
    },
    methods: {
        loadData() {
            axios.get('/api/clients')
                .then(response => {
                    if (response.data.length > 0) {
                        const firstClient = response.data[0];
                        this.firstName = firstClient.firstName;
                        this.lastName = firstClient.lastName;
                        this.accounts = firstClient.accounts;
    
                        // Usar la propiedad createdAt para la fecha de creación
                        this.creationDate = firstClient.accounts[0].createdAt;
    
                        this.loading = false;
                        console.log(response.data);
                    } else {
                        console.warn('No hay clientes disponibles.');
                        this.loading = false;
                    }
                })
                .catch(error => {
                    this.loading = false;
                    console.error('Error al obtener la información del cliente', error);
                    if (error.response) {
                        console.error('Respuesta del servidor:', error.response.data);
                    }
                });
        }
    }
},
)

app.mount("#app");
