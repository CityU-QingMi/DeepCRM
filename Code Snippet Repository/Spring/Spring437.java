	@Bean(name = CacheManagementConfigUtils.CACHE_ASPECT_BEAN_NAME)
	@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
	public AnnotationCacheAspect cacheAspect() {
		AnnotationCacheAspect cacheAspect = AnnotationCacheAspect.aspectOf();
		if (this.cacheResolver != null) {
			cacheAspect.setCacheResolver(this.cacheResolver);
		}
		else if (this.cacheManager != null) {
			cacheAspect.setCacheManager(this.cacheManager);
		}
		if (this.keyGenerator != null) {
			cacheAspect.setKeyGenerator(this.keyGenerator);
		}
		if (this.errorHandler != null) {
			cacheAspect.setErrorHandler(this.errorHandler);
		}
		return cacheAspect;
	}
