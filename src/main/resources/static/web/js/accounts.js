const LOGOUT = "/api/logout"
const { createApp } = Vue;

let app = createApp({
    data() {
        return {
            accounts: [],
            client: {},
            loans: [],
        };
    },
    created() {
        this.loadClientData();
    },
    methods: {
        loadClientData() {
            this.loading = true;
    
            axios.get("/api/clients/current")
                .then(response => {
                    // Añadimos un log para verificar la respuesta del servidor
                    console.log("Server Response:", response.data);
    
                    // Actualizamos la propiedad 'client' con los datos del servidor
                    this.client = response.data;
    
                    // Añadimos un log para verificar las cuentas y préstamos
                    console.log("Accounts:", this.client.accounts);
                    console.log("Loans:", this.client.loans);
    
                    // Actualizamos las propiedades 'accounts' y 'loans'
                    this.accounts = this.client.accounts || [];
                    this.loans = this.client.loans || [];
    
                    // Cambiamos el valor de 'loading' a 'false' para indicar que la carga ha finalizado
                    this.loading = false;
                })
                .catch(error => {
                    // Añadimos un log para verificar cualquier error en la petición
                    console.error("Error fetching client data:", error);
    
                    // Cambiamos el valor de 'loading' a 'false' incluso en caso de error
                    this.loading = false;
                });
        },
        logout() {
            axios.post("/api/logout")
              .then(data => {
                window.location.href = "/web/index.html";
              })
              .catch(error => console.log("Error:", error));
          },

          
        redirectToAccount(accountId) {
            const redirectUrl = `http://localhost:8080/web/account.html?id=${accountId}`;
            window.location.href = redirectUrl;
        },
        goToDetails() {
            window.location.href = "http://localhost:8080/web/account.html?id=1"
        },
        crearCuenta(){
            axios.post("/api/accounts")
            .then(response => {
                console.log(response);
            }).
            catch(error = console.log(error));
        }

    },
}).mount("#app");

