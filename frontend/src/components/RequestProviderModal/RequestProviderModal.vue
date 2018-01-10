<template>
  <b-modal
    v-model="showModal"
    ref="requestProviderModal"
    centered
    size="lg"
    title="Request New Provider"
    hide-footer
    @hidden="onClosedRequestProviderModal">
    <b-form novalidate>
      <b-form-group label="Provider Name:">
        <b-form-input
          name="providerNameInput"
          type="text"
          v-model="requestedProvider.name"
          :state="isValidNameGiven"
          v-validate.initial="{ required: true, regex: '^[a-zA-Z0-9.]{1,255}$' }"/>
          <b-form-invalid-feedback v-show="errors.has('providerNameInput')">Please enter a valid provider name.</b-form-invalid-feedback>
      </b-form-group>
      <b-form-group label="Example Referral Link (will be posted first as reward 🙌):">
        <b-form-input
          name="exampleReferralUrlInput"
          type="text"
          v-model="requestedProvider.exampleReferralUrl"
          :state="isValidExampleReferralUrlGiven"
          v-validate.initial="'required|url'"/>
          <b-form-invalid-feedback v-show="errors.has('exampleReferralUrlInput')">Please enter a valid URL.</b-form-invalid-feedback>
      </b-form-group>
      <b-form-group>
        <b-container fluid>
          <b-row>
            <b-col />
             <b-btn variant="success" @click="onRequestProvider" :disabled="!isRequestProviderFormValid">Submit</b-btn>
            <b-col />
          </b-row>
        </b-container>
      </b-form-group>
      <div v-if="requestedProviders.length > 0">
        <b-table
          small
          :current-page="requestedProvidersTableOptions.currentPage"
          :per-page="requestedProvidersTableOptions.perPage"
          :items="requestedProviders" 
          :fields="requestedProvidersTableOptions.fields">
          <template slot="actions" slot-scope="item">
            <!-- We use @click.stop here to prevent a 'row-clicked' event from also happening -->
            <b-btn variant="success" size="sm" @click.stop="upvote(item.item.id)" :disabled="$session.get('alreadyVotedForProviderRequest')" v-if="isUpvoteEnabledForProviderRequest(item.item)">+1</b-btn>
          </template>
          <template slot="table-caption">
            Every user is only allowed to vote once.
          </template>
        </b-table>
        <div class="justify-content-center row">
          <b-pagination 
            :total-rows="requestedProvidersTableOptions.totalRows" 
            :per-page="requestedProvidersTableOptions.perPage" 
            v-model="requestedProvidersTableOptions.currentPage"/>
        </div>
      </div>
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
      requestedProviders: [],
      requestedProvider: {},
      requestedProvidersTableOptions: {
        fields: [
          {
            key: "name",
            label: "Provider",
            class: ["text-center", "font-weight-light"]
          },
          {
            key: "status",
            class: ["text-center", "font-weight-light"]
          },
          {
            key: "votes",
            class: ["text-center", "font-weight-light"]
          },
          {
            key: "actions",
            class: ["text-center", "font-weight-light"]
          }
        ],
        currentPage: 1,
        perPage: 5,
        totalRows: 0
      },
      apiErrors: []
    };
  },

  created() {
    this.fetchAllProviderRequests();
  },

  watch: {
    show: function(newVal) {
      this.showModal = newVal;
      if (this.showModal) {
        this.fetchAllProviderRequests();
      } else {
        this.requestedProvider = {};
        this.requestedProviders = [];
      }
    }
  },

  computed: {
    isValidNameGiven: function() {
      return !this.$validator.errors.has("providerNameInput");
    },
    isValidExampleReferralUrlGiven: function() {
      return !this.$validator.errors.has("exampleReferralUrlInput");
    },
    isRequestProviderFormValid: function() {
      if (this.isValidNameGiven && this.isValidExampleReferralUrlGiven) {
        return true;
      } else {
        return false;
      }
    }
  },

  methods: {
    fetchAllProviderRequests() {
      axios
        .get(`/api/providers/requests`)
        .then(response => {
          this.requestedProviders = response.data;
          this.requestedProvidersTableOptions.totalRows = this.requestedProviders.length;
          this.enrichRequestedProvidersTableItems();
        })
        .catch(e => {
          this.apiErrors.push(e);
          this.requestedProviders = [];
        });
    },
    enrichRequestedProvidersTableItems() {
      if (this.requestedProviders) {
        this.requestedProviders.forEach(function(item) {
          switch (item.status) {
            case "Requested":
              item._rowVariant = "warning";
              break;
            case "Active":
              item._rowVariant = "success";
              break;
            case "Declined":
              item._rowVariant = "danger";
              break;
            default:
              break;
          }
        });
      }
    },
    isUpvoteEnabledForProviderRequest(providerRequest) {
      if (
        providerRequest.status === "Active" ||
        providerRequest.status === "Declined"
      ) {
        return false;
      }
      return true;
    },
    upvote(requestedProviderId) {
      if (this.$session.get("alreadyVotedForProviderRequest")) {
        return;
      }
      const vm = this;
      axios
        .post("/api/providers/requests/upvote", {
          id: requestedProviderId
        })
        .then(response => {
          vm.$session.set("alreadyVotedForProviderRequest", true);
          vm.fetchAllProviderRequests();
          this.$toasted.success("Provider Request successfully upvoted.");
        })
        .catch(e => {
          this.apiErrors.push(e);
          this.$toasted.error(e.response.data);
        });
    },
    createNewProviderRequest(providerName, exampleReferralUrl) {
      const vm = this;
      axios
        .post("/api/providers/requests", {
          name: providerName,
          exampleReferralUrl: exampleReferralUrl
        })
        .then(response => {
          vm.fetchAllProviderRequests();
          vm.requestedProvider = {};
          this.$toasted.success("Provider Request successfully created.");
        })
        .catch(e => {
          vm.requestedProvider = {};
          this.apiErrors.push(e);
          this.$toasted.error(e.response.data);
        });
    },
    onRequestProvider() {
      this.createNewProviderRequest(
        this.requestedProvider.name,
        this.requestedProvider.exampleReferralUrl
      );
    },
    onClosedRequestProviderModal() {
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
