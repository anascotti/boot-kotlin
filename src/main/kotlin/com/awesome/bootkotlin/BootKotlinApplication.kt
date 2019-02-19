package com.awesome.bootkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BootKotlinApplication

fun main(args: Array<String>) {
	runApplication<BootKotlinApplication>(*args)
}
