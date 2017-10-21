	protected CacheManager getDefaultCacheManager() {
		if (this.cacheManager == null) {
			try {
				this.cacheManager = this.beanFactory.getBean(CacheManager.class);
			}
			catch (NoUniqueBeanDefinitionException ex) {
				throw new IllegalStateException("No unique bean of type CacheManager found. "+
						"Mark one as primary or declare a specific CacheManager to use.");
			}
			catch (NoSuchBeanDefinitionException ex) {
				throw new IllegalStateException("No bean of type CacheManager found. Register a CacheManager "+
						"bean or remove the @EnableCaching annotation from your configuration.");
			}
		}
		return this.cacheManager;
	}
