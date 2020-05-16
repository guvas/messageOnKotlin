package no.message.messageOnKotlin

import no.message.messageOnKotlin.model.Bruker
import no.message.messageOnKotlin.model.Meldinger
import no.message.messageOnKotlin.repository.BrukerRepository
import no.message.messageOnKotlin.repository.MeldingRepository
import nz.net.ultraq.thymeleaf.LayoutDialect
import nz.net.ultraq.thymeleaf.decorators.strategies.AppendingStrategy
import nz.net.ultraq.thymeleaf.decorators.strategies.GroupingStrategy
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.thymeleaf.spring5.SpringTemplateEngine

@SpringBootApplication(scanBasePackages = arrayOf("no.message.messageOnKotlin"))
class MessageOnKotlinApplication : WebMvcConfigurerAdapter()

fun main(args: Array<String>) {
	runApplication<MessageOnKotlinApplication>(*args)
	val engine = SpringTemplateEngine();
	engine.addDialect(LayoutDialect())
	engine.addDialect(LayoutDialect(GroupingStrategy()))
	engine.addDialect(LayoutDialect(AppendingStrategy()))
}

@Override
fun addResourceHandlers(registry: ResourceHandlerRegistry?) {
	registry!!.addResourceHandler("/static/**").addResourceLocations("classpath:/static/*")
}

@Bean
fun initBruker(repository: BrukerRepository) = CommandLineRunner{
	repository.save(Bruker(1L, "Guvanch"))
	repository.save(Bruker(2L, "Test"))
}

@Bean
fun initMeldinger(repository: MeldingRepository) = CommandLineRunner {
	repository.save(Meldinger(1L, "En tekst fra Guvanch", Bruker(1L, "Guvanch"), Bruker(2L, "Test")))
	repository.save(Meldinger(2L, "En tekst fra Test", Bruker(2L, "Test"), Bruker(1L, "Guvanch")))
}