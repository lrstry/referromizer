import Axios from 'axios'

export const axios = Axios.create({
  /* Only used locally to workaround CORS */
  baseURL: `http://localhost:8080`,
  headers: {
    'Access-Control-Allow-Origin': 'http://localhost:8081'
  }
})
