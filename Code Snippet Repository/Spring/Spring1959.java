		@Bean
		public CacheManager cacheManager() {
			SimpleCacheManager cm = new SimpleCacheManager();
			cm.setCaches(Arrays.asList(
					defaultCache(),
					new ConcurrentMapCache("primary"),
					new ConcurrentMapCache("secondary"),
					new ConcurrentMapCache("exception")));
			return cm;
		}
