package com.ahghorab.xenonet.config;

import java.util.concurrent.TimeUnit;

import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.jhipster.config.JHipsterProperties;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
        	cm.createCache(com.ahghorab.xenonet.domain.Server.class.getName(), jcacheConfiguration);
        	cm.createCache(com.ahghorab.xenonet.domain.OveralDiagramConnection.class.getName(), jcacheConfiguration);
        	cm.createCache(com.ahghorab.xenonet.domain.User.class.getName() + ".servers", jcacheConfiguration);
        	cm.createCache(com.ahghorab.xenonet.domain.NetworkCard.class.getName(), jcacheConfiguration);
        	cm.createCache(com.ahghorab.xenonet.domain.Server.class.getName() + ".networkCards", jcacheConfiguration);
        	cm.createCache(com.ahghorab.xenonet.domain.Server.class.getName() + ".switchDiagrams", jcacheConfiguration);
        	cm.createCache(com.ahghorab.xenonet.domain.Server.class.getName() + ".overalDiagramConnections", jcacheConfiguration);
            cm.createCache(com.ahghorab.xenonet.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.ahghorab.xenonet.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.ahghorab.xenonet.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.ahghorab.xenonet.domain.PersistentToken.class.getName(), jcacheConfiguration);
            cm.createCache(com.ahghorab.xenonet.domain.User.class.getName() + ".persistentTokens", jcacheConfiguration);
            
            // jhipster-needle-ehcache-add-entry
            cm.createCache(com.ahghorab.xenonet.domain.HostBaseImg.class.getName(), jcacheConfiguration);
            cm.createCache(com.ahghorab.xenonet.domain.ServerBaseImg.class.getName(), jcacheConfiguration);
            cm.createCache(com.ahghorab.xenonet.domain.VnfBaseImg.class.getName(), jcacheConfiguration);
            cm.createCache(com.ahghorab.xenonet.domain.SwitchBaseImg.class.getName(), jcacheConfiguration);

            cm.createCache(com.ahghorab.xenonet.domain.SwitchDiagram.class.getName(), jcacheConfiguration);
            cm.createCache(com.ahghorab.xenonet.domain.HostDiagram.class.getName(), jcacheConfiguration);
            cm.createCache(com.ahghorab.xenonet.domain.ServerDiagram.class.getName(), jcacheConfiguration);
            cm.createCache(com.ahghorab.xenonet.domain.VnfDiagram.class.getName(), jcacheConfiguration);
            
            cm.createCache(com.ahghorab.xenonet.domain.ServerDiagram.class.getName() + ".serverBaseImg", jcacheConfiguration);
            cm.createCache(com.ahghorab.xenonet.domain.ServerDiagram.class.getName() + ".switchDiagrams", jcacheConfiguration);

            cm.createCache(com.ahghorab.xenonet.domain.VnfDiagram.class.getName() + ".vnfBaseImg", jcacheConfiguration);
            cm.createCache(com.ahghorab.xenonet.domain.VnfDiagram.class.getName() + ".switchDiagrams", jcacheConfiguration);
            
            cm.createCache(com.ahghorab.xenonet.domain.HostDiagram.class.getName() + ".hostBaseImg", jcacheConfiguration);
            cm.createCache(com.ahghorab.xenonet.domain.HostDiagram.class.getName() + ".switchDiagrams", jcacheConfiguration);
            
            cm.createCache(com.ahghorab.xenonet.domain.SwitchDiagram.class.getName() + ".switchBaseImg", jcacheConfiguration);
            cm.createCache(com.ahghorab.xenonet.domain.SwitchDiagram.class.getName() + ".serverDiagrams", jcacheConfiguration);
            cm.createCache(com.ahghorab.xenonet.domain.SwitchDiagram.class.getName() + ".vnfDiagrams", jcacheConfiguration);
            cm.createCache(com.ahghorab.xenonet.domain.SwitchDiagram.class.getName() + ".hostDiagrams", jcacheConfiguration);
            
        };
    }
}








