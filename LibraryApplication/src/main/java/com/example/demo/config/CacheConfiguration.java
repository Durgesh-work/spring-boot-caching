package com.example.demo.config;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfiguration {
	
	@Bean
	CacheManagerCustomizer<ConcurrentMapCacheManager> cutomizer(){
		return new ConcurrentCustomizer();
	}
	
	class ConcurrentCustomizer implements CacheManagerCustomizer<ConcurrentMapCacheManager>{

		@Override
		public void customize(ConcurrentMapCacheManager cacheManager) {
			cacheManager.setAllowNullValues(false);
			System.out.println("customizer invoked");
			
		}
		
	}

}
