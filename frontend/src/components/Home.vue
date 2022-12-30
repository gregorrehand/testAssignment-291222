<template>
Please enter your name and pick the Sectors you are currently involved in.
<br />
<br />
Name:
<input type="text" v-model="reply.name">
<br />
<br />

<select v-model="reply.sectors" multiple>
  <option v-for="sector in sectors" :key="sector.id" :value="sector">
    {{ sector.name }}
  </option>
</select>

<br />
<br />
<input type="checkbox" id="checkbox" v-model="reply.agreeToTerms"/> Agree to terms

<br />
<br />
<input type="submit" value="Save" @click="submitReply">
<div v-if="errorMsg" style="color:red">Error!: {{ errorMsg }}</div>
</template>

<script setup lang="ts">
import { getSectors, postReply, putReply } from '@/api/api'
import IReply from '@/types/reply'
import ISector from '@/types/sector'
import { ref } from '@vue/reactivity'
import { onMounted } from '@vue/runtime-core'

const sectors = ref<ISector[]>([])
const reply = ref<IReply>({} as IReply)
const errorMsg = ref('')

onMounted(async () => {
  try {
    const response = await getSectors()
    sectors.value = response.data
  } catch (error) {
    console.log(error)
  }
})

const submitReply = () => {
  if (reply.value.id) {
    putReply(reply.value).then((response) => {
      if (response.status === 200) {
        reply.value = response.data
        errorMsg.value = ''
      }
    }).catch((error) => {
      console.log(error)
      errorMsg.value = error.response.data
    })
    return
  }
  postReply(reply.value).then((response) => {
    if (response.status === 200) {
      reply.value = response.data
      errorMsg.value = ''
    }
  }).catch((error) => {
    console.log(error)
    errorMsg.value = error.response.data
  })
}

</script>
