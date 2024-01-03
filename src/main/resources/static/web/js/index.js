const { createApp } = Vue;

const app = createApp({
  data() {
    return {
      email: "",
      firstName: "",
      lastName: "",
      password: "",
      showModal: false,
      error: null, 
    };
  },
  methods: {
    login() {
      console.log("Email:", this.email);
      console.log("Password:", this.password);
      axios
        .post("/api/login?email=" + this.email + "&password=" + this.password)
        .then((response) => {
          this.clearData();
          this.showModal = false;
  
          // Muestra el SweetAlert de éxito
          Swal.fire({
            icon: 'success',
            title: 'Inicio de sesión exitoso',
            showConfirmButton: false,
            timer: 1500
          });
  
          // Redirige después de un tiempo (1.5 segundos en este caso)
          setTimeout(() => {
            window.location.href = "../web/accounts.html";
          }, 1500);
        })
        .catch((error) => {
          // Muestra el SweetAlert de error
          Swal.fire({
            icon: 'error',
            title: 'Error en el inicio de sesión',
            text: 'Verifica tus credenciales.',
          });
          console.error(error);
        });
    },
    register() {
      axios
        .post(
          "/api/clients?firstName=" +
            this.firstName +
            "&lastName=" +
            this.lastName +
            "&email=" +
            this.email +
            "&password=" +
            this.password
        )
        .then((response) => {
          console.log(response);
          this.clearData();
  
          // Muestra el SweetAlert de éxito para el registro
          Swal.fire({
            icon: 'success',
            title: 'Registro exitoso',
            showConfirmButton: false,
            timer: 1500
          });
  
          this.showModal = false;
        })
        .catch((error) => {
          // Muestra el SweetAlert de error para el registro
          Swal.fire({
            icon: 'error',
            title: 'Error en el registro',
            text: 'Hubo un problema al registrar la cuenta. Inténtalo de nuevo.',
          });
          console.error(error);
         
        });
    },
    openLoginModal() {
      this.showModal = true;
    },
    closeLoginModal() {
      this.showModal = false;
    },
    clearData() {
      this.firstName = "";
      this.lastName = "";
      this.email = "";
      this.password = "";
    },
  },
}).mount("#app");

document.addEventListener('DOMContentLoaded', function () {
  const scroll = new SmoothScroll('a[href*="#"]', {
    speed: 800,
    offset: 80, 
  });
})
