package ru.alex.http3demo.config;

import org.springframework.boot.ssl.SslBundle;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;
import reactor.netty.http.Http3SslContextSpec;
import reactor.netty.http.HttpProtocol;

import java.time.Duration;

@Component
public class Http3NettyWebServerCustomizer implements WebServerFactoryCustomizer<NettyReactiveWebServerFactory> {
    @Override
    public void customize(NettyReactiveWebServerFactory factory) {
        factory.addServerCustomizers(server -> {
            SslBundle sslBundle = factory.getSslBundles().getBundle("server-http3");
            Http3SslContextSpec sslContextSpec = Http3SslContextSpec
                    .forServer(sslBundle.getManagers().getKeyManagerFactory(), sslBundle.getKey().getPassword());

            return server
                    .protocol(HttpProtocol.HTTP3)
                    .secure(spec -> spec.sslContext(sslContextSpec))
                    .http3Settings(spec -> spec
                            .idleTimeout(Duration.ofSeconds(5))
                            .maxData(10_000_000)
                            .maxStreamDataBidirectionalRemote(1_000_000)
                            .maxStreamsBidirectional(100));
        });
    }
}