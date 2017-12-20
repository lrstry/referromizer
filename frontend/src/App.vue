<template>
  <div>
    <div id="referromizerLogo">
        <img src="./assets/img/logo.png" alt="Logo" />
        <div>
          <h4>Application for sharing and retrieving random referral links to various services.</h4>
        </div>
    </div>
    <div>
      <v-submit-referral-modal v-on:closeModal="closeModal()" :show="isSubmitReferralModalVisible"></v-submit-referral-modal>
    </div>
    <b-container id="content">
      <b-row align-v="center">
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
    <b-navbar id="footerBar" fixed="bottom">
      <b-nav-item href="https://github.com/treyla/referromizer" target="_blank">
        <img src="./assets/img/GitHub-Mark-120px-plus.png">
      </b-nav-item>
    </b-navbar>
  </div>
</template>

<script>
import Provider from "./components/Provider/";
import Referral from "./components/Referral/";
import SubmitReferralModal from "./components/SubmitReferralModal/";

export default {
  name: "app",

  components: {
    "v-provider": Provider,
    "v-referral": Referral,
    "v-submit-referral-modal": SubmitReferralModal
  },

  data() {
    return {
      isSubmitReferralModalVisible: false,
      selectedProviderId: ""
    };
  },

  methods: {
    closeModal() {
      this.isSubmitReferralModalVisible = false;
    },
    changeSelectedProvider(newSelectedProviderId) {
      this.selectedProviderId = newSelectedProviderId;
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

#referromizerLogo {
  z-index: 0;
  color: white;
  text-align: center;
  padding-bottom: 2rem;
}

#provider span {
  color: white;
}

#content {
  z-index: 1;
  padding-top: 6rem;
  padding-bottom: 6rem;
}

#footerBar {
  z-index: 0;
  overflow: hidden;
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
</style>
