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
      {{ sector.label }}
    </option>
  </select>
  <div v-if="reply.sectors">
    Selected sectors: {{ reply.sectors.map((sector) => sector.name).join(', ') }}
  </div>

<br />
<br />
<input type="checkbox" id="checkbox" v-model="reply.agreeToTerms" /> Agree to terms

<br />
<br />
<div v-if="reply.id">
  <input type="submit" style="margin: 5px 10px;" value="Start a new answer" @click="clearReply">
  <input type="submit" style="margin: 5px 10px;" value="Change your answer" @click="changeReply">
</div>
<div v-else>
  <input type="submit" value="Submit answer" @click="submitReply">
</div>

<div v-if="errorMsg" style="color:red">Error!: {{ errorMsg }}</div>
</template>

<script setup lang="ts">
import { clearSession, getSectors, getSessionReply, postReply, putReply } from '@/api/api'
import IReply from '@/types/reply'
import ISector from '@/types/sector'
import { ref } from '@vue/reactivity'
import { onBeforeMount } from '@vue/runtime-core'

const sectors = ref<ISector[]>([])
const reply = ref<IReply>({} as IReply)
const errorMsg = ref('')

onBeforeMount(async () => {
  try {
    const response = await getSectors()
    sectors.value = flattenSectors(response.data, 0)
    const existingReply = await getSessionReply()
    if (existingReply) {
      reply.value = existingReply.data
    }
  } catch (error) {
    console.log(error)
  }
})

const submitReply = () => {
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

const changeReply = () => {
  putReply(reply.value).then((response) => {
    if (response.status === 200) {
      reply.value = response.data
      errorMsg.value = ''
    }
  }).catch((error) => {
    console.log(error)
    errorMsg.value = error.response.data
  })
}

const clearReply = () => {
  clearSession().then((response) => {
    if (response.status === 200) {
      reply.value = {} as IReply
    }
  })
}

const flattenSectors = (sectors: ISector[], indentation: number): ISector[] => {
  return sectors.reduce((acc, sector) => {
    const indentedSector = {
      ...sector,
      label: '-'.repeat(indentation) + sector.name // Hacky solution for identation. In real life I would probably use CSS
    }
    return acc.concat(indentedSector, flattenSectors(sector.subSectors, indentation + 2))
  }, [] as ISector[])
}

</script>
