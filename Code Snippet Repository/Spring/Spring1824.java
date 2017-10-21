	protected KeyGenerator determineKeyGenerator(CacheDefaults defaults, Class<? extends CacheKeyGenerator> candidate) {
		if (CacheKeyGenerator.class != candidate) {
			return new KeyGeneratorAdapter(this, getBean(candidate));
		}
		else if (defaults != null && CacheKeyGenerator.class != defaults.cacheKeyGenerator()) {
			return new KeyGeneratorAdapter(this, getBean(defaults.cacheKeyGenerator()));
		}
		else {
			return getDefaultKeyGenerator();
		}
	}
