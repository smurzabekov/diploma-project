import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

@Configuration
class JacksonConfiguration {

    @Bean
    @Primary
    fun jacksonBuilder(): Jackson2ObjectMapperBuilder = Jackson2ObjectMapperBuilder()
        .indentOutput(true)
        .featuresToDisable(
            SerializationFeature.INDENT_OUTPUT,
            SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
            DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES
        )
        .modules(kotlinModule(), JavaTimeModule())

    @Bean
    @Primary
    fun objectMapper(jacksonBuilder: Jackson2ObjectMapperBuilder): ObjectMapper =
        (jacksonBuilder.build() as ObjectMapper).setSerializationInclusion(JsonInclude.Include.NON_NULL)

    @Bean
    @Primary
    fun jacksonMessageConverter(objectMapper: ObjectMapper) = MappingJackson2HttpMessageConverter(objectMapper)
}