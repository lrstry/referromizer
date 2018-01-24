<template>
  <div>
    <b-input-group id="referralInputGroup">
      <b-input-group-button>
        <b-button
          class="btnCustom btnCustom-default"
          @click="onSubmitNewReferralPress"
          v-b-tooltip.hover.bottom
          title="Add Referral">
          <icon
            name="plus"
            scale="1.8"
            label="Add Referral"/>
        </b-button>
      </b-input-group-button>
      <b-input-group-addon id="hitsBadge">
        <b-badge
          v-if="isReferralPresent"
          pill
          variant="dark">
          {{ referral.hits }} {{ hitsBadgeText }}
        </b-badge>
      </b-input-group-addon>
      <b-form-input
        id="referralInput"
        type="text"
        title="Number of hits"
        v-model="referralInputValue"
        disabled/>
      <b-input-group-button>
        <b-button
          class="btnCustom"
          id="gotoLinkButton"
          v-b-tooltip.hover.bottom
          :href="referral.refUrl"
          title="Goto referral link"
          target="_blank"
          :disabled="!isRefUrlPresent"
          variant="primary">
          <icon
            name="external-link"
            scale="1.8"
            label="Goto referral link"/>
        </b-button>
      </b-input-group-button>
      <b-input-group-button>
        <b-button
          class="btnCustom"
          id="copyButton"
          v-clipboard:copy="referral.refId"
          v-clipboard:success="onCopySuccess"
          v-b-tooltip.hover.bottom
          title="Copy to clipboard"
          :disabled="!isRefIdGiven"
          variant="warning">
          <icon
            name="clipboard"
            scale="1.8"
            label="Copy to clipboard"/>
        </b-button>
      </b-input-group-button>
    </b-input-group>
  </div>
</template>

<script>
// import { axios } from '../../config/http-commons'
import axios from "axios";

export default {
  props: ["selectedProviderId"],

  data() {
    return {
      hitsBadgeText: "",
      isProviderSelected: false,
      isReferralPresent: false,
      isRefIdPresent: false,
      isRefUrlPresent: false,
      referral: {},
      apiErrors: []
    };
  },

  watch: {
    selectedProviderId: function(val, oldVal) {
      if (val && val !== oldVal) {
        this.isProviderSelected = true;
        this.fetchRandomReferralForProvider(val);
      } else {
        this.isProviderSelected = false;
      }
    },
    referral: function(val, oldVal) {
      if (val) {
        this.isReferralPresent = true;
        if (val !== oldVal) {
          if (val.refId) {
            this.isRefIdPresent = true;
          } else {
            this.isRefIdPresent = false;
          }

          if (val.refUrl) {
            this.isRefUrlPresent = true;
          } else {
            this.isRefUrlPresent = false;
          }

          if (val.hits === 1) {
            this.hitsBadgeText = "Hit";
          } else {
            this.hitsBadgeText = "Hits";
          }
        }
      } else {
        this.isReferralPresent = false;
        this.isRefUrlPresent = false;
        this.isRefIdPresent = false;
      }
    }
  },

  computed: {
    referralInputValue: {
      get: function() {
        if (!this.isProviderSelected) {
          return "Please select a provider to retrieve a random referral.";
        }
        if (this.isRefIdPresent) {
          return this.referral.refId;
        } else if (!this.isRefIdPresent & this.isRefUrlPresent) {
          return "No referral id present. Please use link.";
        } else {
          return "No referrals for selected provider present.";
        }
      },
      set: function(newValue) {
        return newValue;
      }
    },
    isRefIdGiven: function() {
      if (
        this.referral &&
        this.referral.refId &&
        Object.keys(this.referral.refId).length > 0
      ) {
        return true;
      } else {
        return false;
      }
    }
  },

  methods: {
    fetchRandomReferralForProvider(providerId) {
      if (providerId) {
        axios
          .get(`/api/referrals/` + providerId)
          .then(response => {
            this.referral = response.data;
            if (response.data.id) {
              this.referral = response.data;
            }
          })
          .catch(e => {
            this.apiErrors.push(e);
            this.isRefUrlPresent = false;
          });
      }
    },
    onSubmitNewReferralPress() {
      if (this.$parent) {
        this.$parent.isSubmitReferralModalVisible = true;
      }
    },
    onCopySuccess() {
      this.$toasted.info("Copied to clipboard!");
    }
  }
};
</script>

<style lang="scss" scoped>
#referralInput {
  border: none;
  text-align: center;
}

#referralInputGroup .btn {
  padding-top: 7px;
  padding-right: 15px;
  padding-bottom: 0px;
  padding-left: 15px;
}

.fa-icon {
  // width: 1em
  // height: 1em
  /* or any other relative font sizes */
  /* You would have to include the following two lines to make this work in Safari */
  max-width: 100%;
  max-height: 100%;
  color: black;
}
</style>
