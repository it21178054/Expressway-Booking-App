package com.example.firebasekotlin.models

import android.security.identity.AccessControlProfileId

data class CustomerModel(
    var cuId: String?=null,
    var cuName: String?=null,
    var cuNic: String?=null,
    var cuTpNumber: String?=null,
    var cuEmail: String?=null,
    var cuInquiry:String?=null

)