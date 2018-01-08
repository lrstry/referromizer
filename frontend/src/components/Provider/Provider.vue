<template>
  <div>
    <b-form-group label="Please select a provider:">
      <b-form-select
        v-model="selectedProvider"
        class="mb-3"
        @input="onSelectProvider">
        <option
          v-for="provider in providers"
          :key="provider.id"
          :value="provider">{{ provider.name }}</option>
      </b-form-select>
    </b-form-group>
  </div>
</template>

<script>
// import { axios } from "../../config/http-commons";
import axios from "axios";

export default {
  data() {
    return {
      isProviderSelected: false,
      selectedProvider: {},
      providers: [],
      apiErrors: []
    };
  },

  created() {
    axios
      .get(`/api/providers`)
      .then(response => {
        this.providers = response.data;
        this.selectedProvider = this.providers[0];
        // @TODO: Somehow change event on select is not triggered; find better solution in future
        this.onSelectProvider(this.selectedProvider);
      })
      .catch(e => {
        this.apiErrors.push(e);
        this.providers = [];
      });
  },

  methods: {
    onSelectProvider(provider) {
      if (provider && provider.id) {
        this.$emit("providerChanged", provider.id);
        this.isProviderSelected = true;
        this.selectedProvider = provider;
      } else {
        this.isProviderSelected = false;
        this.selectedProvider = {};
      }
    }
  }
};
</script>
