<template>
  <div>
    <div>
      <b-container id="referromizerLogo">
        <img src="./assets/img/logo.png">
        <div class="hide-on-landscape">
          <h4>Application for sharing and retrieving random referral links to various services.</h4>
        </div>
      </b-container>
    </div>
    <div>
      <v-submit-referral-modal v-on:closeModal="closeSubmitReferralModal()" :show="isSubmitReferralModalVisible"></v-submit-referral-modal>
      <v-request-provider-modal v-on:closeModal="closeRequestProviderModal()" :show="isRequestProviderModalVisible"></v-request-provider-modal>
      <v-donate-modal v-on:closeModal="closeDonateModal()" :show="isDonateModalVisible"></v-donate-modal>
    </div>
    <b-container id="content">
      <b-row>
        <b-col>
          <div id="provider">
            <v-provider v-on:providerChanged="changeSelectedProvider($event)"></v-provider>
          </div>
          <div id="referral">
            <v-referral :selectedProviderId="selectedProviderId"></v-referral>
          </div>
        </b-col>
      </b-row>
    </b-container>
    <b-container id="footerBar">
      <b-button-group>
        <b-button size="sm" href="https://github.com/treyla/referromizer" target="_blank">
          GitHub
        </b-button>
        <b-button size="sm" variant="warning" @click="onRequestProviderPress">
          Request Provider
        </b-button>
        <b-button size="sm" variant="success" @click="onDonatePress">
          Donate
        </b-button>
        </b-button-group>
    </b-container>
  </div>
</template>

<script>
import Provider from "./components/Provider/";
import Referral from "./components/Referral/";
import SubmitReferralModal from "./components/SubmitReferralModal/";
import DonateModal from "./components/DonateModal/";
import RequestProviderModal from "./components/RequestProviderModal/";

export default {
  name: "app",

  components: {
    "v-provider": Provider,
    "v-referral": Referral,
    "v-submit-referral-modal": SubmitReferralModal,
    "v-donate-modal": DonateModal,
    "v-request-provider-modal": RequestProviderModal
  },

  data() {
    return {
      isSubmitReferralModalVisible: false,
      isDonateModalVisible: false,
      isRequestProviderModalVisible: false,
      selectedProviderId: ""
    };
  },

  methods: {
    closeSubmitReferralModal() {
      this.isSubmitReferralModalVisible = false;
    },
    closeRequestProviderModal() {
      this.isRequestProviderModalVisible = false;
    },
    closeDonateModal() {
      this.isDonateModalVisible = false;
    },
    changeSelectedProvider(newSelectedProviderId) {
      this.selectedProviderId = newSelectedProviderId;
    },
    onDonatePress() {
      if (!this.isDonateModalVisible) {
        this.isDonateModalVisible = true;
      }
    },
    onRequestProviderPress() {
      if (!this.isRequestProviderModalVisible) {
        this.isRequestProviderModalVisible = true;
      }
    }
  }
};
</script>

<style lang="scss">
@import url("https://fonts.googleapis.com/css?family=Open+Sans");
@import "compass";

html {
  height: 100%;
}

body {
  height: 100%;
  margin: 0;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background: rgba(6, 63, 99, 1);
  background: -moz-linear-gradient(
    top,
    rgba(6, 63, 99, 1) 0%,
    rgba(1, 31, 49, 1) 100%
  );
  background: -webkit-gradient(
    left top,
    left bottom,
    color-stop(0%, rgba(6, 63, 99, 1)),
    color-stop(100%, rgba(1, 31, 49, 1))
  );
  background: -webkit-linear-gradient(
    top,
    rgba(6, 63, 99, 1) 0%,
    rgba(1, 31, 49, 1) 100%
  );
  background: -o-linear-gradient(
    top,
    rgba(6, 63, 99, 1) 0%,
    rgba(1, 31, 49, 1) 100%
  );
  background: -ms-linear-gradient(
    top,
    rgba(6, 63, 99, 1) 0%,
    rgba(1, 31, 49, 1) 100%
  );
  background: linear-gradient(
    to bottom,
    rgba(6, 63, 99, 1) 0%,
    rgba(1, 31, 49, 1) 100%
  );
  filter: progid:DXImageTransform.Microsoft.gradient(
      startColorstr="#063f63",
      endColorstr="#011f31",
      GradientType=0
    );
}

h1 {
  font-size: 20px;
  font-size: 1.15rem;
}

#referromizerLogo {
  z-index: 0;
  color: white;
  text-align: center;
  padding-bottom: 2rem;
}

#referromizerLogo img {
  z-index: 0;
  display: block;
  margin: auto;
  width: 100%;
}

#provider span {
  color: white;
}

#content {
  z-index: 1;
  position: absolute;
  padding: 20px;
  left: 50%;
  top: 50%;
  -webkit-transform: translateX(-50%) translateY(-50%);
  transform: translateX(-50%) translateY(-50%);
}

#footerBar {
  position: fixed;
  padding: 2rem;
  margin-bottom: 1rem;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
}

.fixed-bottom li {
  margin: auto;
  list-style-type: none;
}

.fixed-bottom li img {
  width: 3rem;
  height: auto;
  padding-bottom: 1rem;
}

.btn:active,
.btn:focus,
.btn.active,
.btn:active:focus {
  background-image: none;
  outline: 0;
  -webkit-box-shadow: none;
  box-shadow: none;
}

@media screen and (orientation: landscape) and (max-height: 420px) {
  .hide-on-landscape {
    display: none;
  }
}

.twitter-btn {
  background-color: #4694da;
  border-color: #4694da;
}
</style>
