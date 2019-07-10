package xyz.selyu.lunar.util

import java.nio.ByteBuffer
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.util.UUID

fun uuidToBytes(uuid: UUID): ByteArray {
    val byteBuffer = ByteBuffer.wrap(ByteArray(16))

    byteBuffer.putLong(uuid.mostSignificantBits)
    byteBuffer.putLong(uuid.leastSignificantBits)

    return byteBuffer.array()
}

fun bytesToUUID(bytes: ByteArray): UUID {
    val buffer = ByteBuffer.wrap(bytes)

    return UUID(buffer.long, buffer.long)
}

fun writeInt(value: Int): ByteArray {
    val outputStream = ByteArrayOutputStream()
    val dataOutputStream = DataOutputStream(outputStream)

    dataOutputStream.writeInt(value)

    dataOutputStream.close()
    outputStream.close()

    return outputStream.toByteArray()
}

fun writeColour(red: Int = 255, green: Int = 255, blue: Int = 255): ByteArray {
    val outputStream = ByteArrayOutputStream()

    outputStream.write(255)
    outputStream.write(red)
    outputStream.write(green)
    outputStream.write(blue)

    outputStream.close()

    return outputStream.toByteArray()
}

fun writeBoolean(value: Boolean): ByteArray {
    val outputStream = ByteArrayOutputStream()
    val dataOutputStream = DataOutputStream(outputStream)

    dataOutputStream.writeBoolean(value)

    dataOutputStream.close()
    outputStream.close()

    return outputStream.toByteArray()
}

fun writeVarInt(value: Int): ByteArray {
    var value = value
    val outputStream = ByteArrayOutputStream()

    while (value and -128 != 0) {
        outputStream.write(value and 127 or 128)
        value = value ushr 7
    }

    outputStream.write(value)

    outputStream.close()

    return outputStream.toByteArray()
}

fun writeString(value: String): ByteArray {
    val outputStream = ByteArrayOutputStream()
    val bytes = value.toByteArray(Charsets.UTF_8)

    outputStream.write(writeVarInt(bytes.size))
    outputStream.write(bytes)

    outputStream.close()

    return outputStream.toByteArray()
}

fun writeDouble(value: Double): ByteArray {
    val bytes = ByteArray(8)

    ByteBuffer.wrap(bytes).putDouble(value)

    return bytes
}

fun writeLong(value: Long): ByteArray {
    val outputStream = ByteArrayOutputStream()
    val dataOutputStream = DataOutputStream(outputStream)

    dataOutputStream.writeLong(value)

    dataOutputStream.close()
    outputStream.close()

    return outputStream.toByteArray()
}

fun writeFloat(value: Float): ByteArray {
    val outputStream = ByteArrayOutputStream()
    val dataOutputStream = DataOutputStream(outputStream)

    dataOutputStream.writeFloat(value)

    dataOutputStream.close()
    outputStream.close()

    return outputStream.toByteArray()
}

fun writeMap(players: Map<UUID, String>): ByteArray {
    val outputStream = ByteArrayOutputStream()

    outputStream.write(writeVarInt(players.size))

    for ((key, value) in players) {
        outputStream.write(uuidToBytes(key))
        outputStream.write(writeString(value))
    }

    outputStream.close()

    return outputStream.toByteArray()
}