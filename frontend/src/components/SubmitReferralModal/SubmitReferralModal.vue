<template>
  <b-modal
    v-model="showModal"
    :ok-disabled="!isSubmitReferralFormValid"
    ref="submitReferralModal"
    centered
    title="Submit Referral"
    @hidden="onClosedSubmitReferralModal"
    @ok="onSubmitNewReferral"
    @cancel="onCancelNewReferral">
    <b-form novalidate>
      <b-form-group label="Provider:">
        <b-form-select
          v-model="selectedProvider"
          :state="isValidProviderSelected">
          <option
            name="providerSelect"
            v-for="provider in providers"
            :key="provider.id"
            :value="provider">
            {{ provider.name }}
          </option>
        </b-form-select>
        <b-form-invalid-feedback v-show="errors.has('providerSelect')">Please select a provider.</b-form-invalid-feedback>
      </b-form-group>
      <b-form-group label="Referral Link:">
        <b-form-input
          v-validate.initial="{ required: true, regex: selectedProvider.refUrlRegex }"
          :state="isValidRefUrlGiven"
          name="refUrlInput"
          type="text"
          v-model="refUrl"/>
        <b-form-invalid-feedback v-show="errors.has('refUrlInput')">Please provide a valid URL.</b-form-invalid-feedback>
      </b-form-group>
    </b-form>
  </b-modal>
</template>

<script>
// import { axios } from "../../config/http-commons";
import axios from "axios";

export default {
  props: ["show"],

  data() {
    return {
      showModal: false,
      selectedProvider: {},
      providers: [],
      refUrl: "",
      apiErrors: []
    };
  },

  created() {
    axios
      .get(`/api/providers`)
      .then(response => {
        this.providers = response.data;
      })
      .catch(e => {
        this.apiErrors.push(e);
        this.providers = [];
      });
  },

  watch: {
    show: function(newVal) {
      this.showModal = newVal;
    }
  },

  computed: {
    isValidProviderSelected: function() {
      return this.selectedProvider.id !== undefined;
    },
    isValidRefUrlGiven: function() {
      return !this.$validator.errors.has("refUrlInput");
    },
    isSubmitReferralFormValid: function() {
      if (this.isValidRefUrlGiven && this.selectedProvider.id) {
        return true;
      } else {
        return false;
      }
    }
  },

  methods: {
    createNewReferral(providerId, refUrl) {
      axios
        .post("/api/referrals", {
          provider: { id: providerId },
          refUrl: refUrl
        })
        .then(response => {
          this.$toasted.success("Referral successfully created.");
        })
        .catch(e => {
          this.apiErrors.push(e);
          this.$toasted.error(e.response.data);
        });
    },
    onSubmitNewReferral(event) {
      if (this.isSubmitReferralFormValid && this.isValidRefUrlGiven) {
        this.showModal = false;
        this.createNewReferral(this.selectedProvider.id, this.refUrl);
      } else {
        this.showModal = true;
      }
    },
    onCancelNewReferral(event) {
      this.showModal = false;
      this.selectedProvider = {};
      this.refUrl = "";
    },
    onClosedSubmitReferralModal() {
      this.$emit("closeModal");
      this.selectedProvider = {};
      this.refUrl = "";
      this.showModal = false;
    }
  },
  events: {
    closeModal() {
      this.showModal = false;
      this.selectedProvider = {};
      this.refUrl = "";
    }
  }
};
</script>
