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
            
            axios("/api/clients/2")
                .then(response => {
                    this.client = response.data;
                    this.accounts = this.client.accounts;
                    console.log("Accounts:", this.accounts);
                    this.loans = response.data.loans;
                    console.log(this.loans);
                })
                .catch(error => console.error(error));
        },
        redirectToAccount(accountId) {
            const redirectUrl = `http://localhost:8080/web/account.html?id=${accountId}`;
            window.location.href = redirectUrl;
        },
    },
}).mount("#app");
