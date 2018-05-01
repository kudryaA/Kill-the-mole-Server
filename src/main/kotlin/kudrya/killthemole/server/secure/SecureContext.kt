package kudrya.killthemole.server.secure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SecureContext {
    @Bean
    fun secureConfig(): SecureController = SecureController()
}