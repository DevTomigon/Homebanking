const {createApp} = Vue

let app = createApp({
    data(){
        return{
            data : [],
            firstName: "",
            lastName:"",
            email: ""
        }
    },
    created(){
        this.loadData();  
         
    },
    methods:{
        loadData(){
            axios("/clients")
            .then(response =>{
                this.data = response
                this.clients = response.data._embedded.clients
                console.log(this.clients);
            })
            .catch(error => console.log(error))
        }, 
        addClient() {
            axios.post("/clients", {
                firstName: this.firstName,
                lastName: this.lastName,
                email: this.email
            })
            .then(response => {
                this.loadData(); 
                this.firstName = "";
                this.lastName = "";
                this.email = "";
            })
            .catch(error => console.log(error));
        }
        
    }
}).mount("#app")