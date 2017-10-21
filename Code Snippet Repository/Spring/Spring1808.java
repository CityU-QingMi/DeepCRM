	@Bean(name = "")
	@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
	public JCacheOperationSource cacheOperationSource() {
		DefaultJCacheOperationSource source = new DefaultJCacheOperationSource();
		if (this.cacheManager != null) {
			source.setCacheManager(this.cacheManager);
		}
		if (this.keyGenerator != null) {
			source.setKeyGenerator(this.keyGenerator);
		}
		if (this.cacheResolver != null) {
			source.setCacheResolver(this.cacheResolver);
		}
		if (this.exceptionCacheResolver != null) {
			source.setExceptionCacheResolver(this.exceptionCacheResolver);
		}
		return source;
	}
