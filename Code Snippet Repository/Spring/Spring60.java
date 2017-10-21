	private Object getSingletonAspectInstance(Class<?> aspectClass) {
		// Quick check without a lock...
		Object instance = aspectCache.get(aspectClass);
		if (instance == null) {
			synchronized (aspectCache) {
				// To be safe, check within full lock now...
				instance = aspectCache.get(aspectClass);
				if (instance != null) {
					return instance;
				}
				instance = new SimpleAspectInstanceFactory(aspectClass).getAspectInstance();
				aspectCache.put(aspectClass, instance);
			}
		}
		return instance;
	}
