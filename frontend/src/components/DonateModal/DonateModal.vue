<template>
  <b-modal
    v-model="showModal"
    ref="donateModal"
    centered
    ok-only
    title="Donate"
    @hidden="onClosedDonateModal">
    <b-form>
      <b-form-group label="Wallet:">
        <b-form-select
          v-model="selectedWallet">
          <option
            name="walletSelect"
            v-for="wallet in wallets"
            :key="wallet.id"
            :value="wallet">
            {{ wallet.name }}
          </option>
        </b-form-select>
      </b-form-group>
      <b-form-group label="Address:">
        <b-form-input
          disabled
          name="walletAddress"
          type="text"
          v-model="selectedWallet.address"/>
      </b-form-group>
      <b-form-group :v-show="isSelectedWalletQrCodeVisible">
        <b-img center fluid :src="selectedWalletQrCodeUrl" alt="QR Code to Public Donation Address" />
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
      selectedWallet: {},
      isSelectedWalletQrCodeVisible: false,
      wallets: [],
      apiErrors: []
    };
  },

  computed: {
    selectedWalletQrCodeUrl: function() {
      if (this.selectedWallet && this.selectedWallet.address) {
        this.isSelectedWalletQrCodeVisible = true;
        return (
          "https://chart.googleapis.com/chart?chs=250x250&cht=qr&chl=" +
          this.selectedWallet.address
        );
      } else {
        this.isSelectedWalletQrCodeVisible = false;
        return "";
      }
    }
  },

  created() {
    axios
      .get(`/api/wallets`)
      .then(response => {
        this.wallets = response.data;
        this.selectedWallet = this.wallets[0];
      })
      .catch(e => {
        this.apiErrors.push(e);
        this.wallets = [];
      });
  },

  watch: {
    show: function(newVal) {
      this.showModal = newVal;
    }
  },

  methods: {
    onClosedDonateModal() {
      this.$emit("closeModal");
      this.showModal = false;
    }
  },
  events: {
    closeModal() {
      this.showModal = false;
    }
  }
};
</script>
