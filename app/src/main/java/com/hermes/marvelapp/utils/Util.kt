package com.hermes.marvelapp.utils

import com.hermes.marvelapp.data.local.CharacterEntity
import java.math.BigInteger
import java.security.MessageDigest

fun getHash(timestamp: String, privateKey: String, publicKey: String): String {
    val hash = timestamp + privateKey + publicKey
    val messageDigest = MessageDigest.getInstance("MD5")
    return BigInteger(1, messageDigest.digest(hash.toByteArray())).toString(16).padStart(32, '0')
}

fun List<String>.comicsToString() = this.joinToString(separator = ", ")
fun List<String>.charactersToString() = this.joinToString(separator = ", ")

fun testCharacter() = CharacterEntity(
    id = 12,
    apiId = 12,
    name = "Hulk",
    description =  "Caught in a gamma bomb explosion while trying to save the life of a teenager, Dr. Bruce Banner was transformed into the incredibly powerful creature called the Hulk. An all too often misunderstood hero, the angrier the Hulk gets, the stronger the Hulk gets.",
    path = "http://i.annihil.us/u/prod/marvel/i/mg/5/a0/538615ca33ab0",
    extension = "jpg",
    comics = "Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos, e vem sendo utilizado desde o século XVI, quando um impressor desconhecido pegou uma bandeja de tipos e os embaralhou para fazer um livro de modelos de tipos. Lorem Ipsum sobreviveu não só a cinco séculos, como também ao salto para a editoração eletrônica, permanecendo essencialmente inalterado. Se popularizou na década de 60, quando a Letraset lançou decalques contendo passagens de Lorem Ipsum, e mais recentemente quando passou a ser integrado a softwares de editoração eletrônica como Aldus PageMaker."
)