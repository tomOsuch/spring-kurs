package pl.tomaszosuch.springkurs.shop;

import org.springframework.context.annotation.*;
import pl.tomaszosuch.springkurs.shop.time.SystemTimeProvider;
import pl.tomaszosuch.springkurs.shop.time.TimeProvider;

@EnableAspectJAutoProxy
@ComponentScan
@Configuration
public class ShopConfiguration {

    @Bean
    public TimeProvider systemTimeProvider() {
        return new SystemTimeProvider();
    }
}
