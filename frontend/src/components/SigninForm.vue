<template>
  <v-form @submit.prevent="login">
    <FormAlert :message="error" />
    <v-text-field v-model="email" required label="E-mail" />
    <v-text-field
      v-model="password"
      :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
      :type="showPassword ? 'text' : 'password'"
      required
      label="Password"
      @click:append="showPassword = !showPassword"
    />
    <v-btn
      type="submit"
      color="primary"
      class="my-2"
      :loading="loading"
      :disabled="loading"
      >Sign in</v-btn
    >
  </v-form>
</template>
<script>
import FormAlert from "./FormAlert.vue";

export default {
  data: () => {
    return {
      showPassword: false,
      loading: false,
      email: "",
      password: "",
      error: "",
    };
  },
  methods: {
    login() {
      this.loading = true;
      this.$store
        .dispatch("auth/login", {
          email: this.email,
          password: this.password,
        })
        .then(() => {
          this.$router.push("/");
        })
        .catch((error) => {
          this.loading = false;
          if (error.response?.status == 401) {
            this.error =
              "We are unable to identify your credentials, please try again.";
          } else {
            this.$store.dispatch(
              "alert/push",
              "Sorry, we encountered error while trying to sign you in. Please try again later"
            );
          }
        });
    },
  },
  components: { FormAlert },
};
</script>
