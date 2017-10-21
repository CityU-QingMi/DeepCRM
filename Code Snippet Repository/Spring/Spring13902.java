	@Nullable
	private String getBeanNameByType(WebApplicationContext wac, Class<?> endpointClass) {
		String wacId = wac.getId();

		Map<Class<?>, String> beanNamesByType = cache.get(wacId);
		if (beanNamesByType == null) {
			beanNamesByType = new ConcurrentHashMap<>();
			cache.put(wacId, beanNamesByType);
		}

		if (!beanNamesByType.containsKey(endpointClass)) {
			String[] names = wac.getBeanNamesForType(endpointClass);
			if (names.length == 1) {
				beanNamesByType.put(endpointClass, names[0]);
			}
			else {
				beanNamesByType.put(endpointClass, NO_VALUE);
				if (names.length > 1) {
					throw new IllegalStateException("Found multiple @ServerEndpoint's of type [" +
							endpointClass.getName() + "]: bean names " + Arrays.asList(names));
				}
			}
		}

		String beanName = beanNamesByType.get(endpointClass);
		return (NO_VALUE.equals(beanName) ? null : beanName);
	}
