package com.reloader.firestoredemo.Entidades

import java.io.Serializable
import java.util.*

data class ESms(
    val estado_id: Any,
    val sms_destinatario: String,
    val sms_envio_error: String,
    val sms_envio_estado: Boolean,
    val sms_envio_fecha: Date,
    val sms_fecha_registro: Date,
    val sms_id: Any,
    val sms_mensaje: String
) : Serializable