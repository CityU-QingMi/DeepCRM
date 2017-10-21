	static Map<String, List<ContextConfigurationAttributes>> buildContextHierarchyMap(Class<?> testClass) {
		final Map<String, List<ContextConfigurationAttributes>> map = new LinkedHashMap<>();
		int hierarchyLevel = 1;

		for (List<ContextConfigurationAttributes> configAttributesList : resolveContextHierarchyAttributes(testClass)) {
			for (ContextConfigurationAttributes configAttributes : configAttributesList) {
				String name = configAttributes.getName();

				// Assign a generated name?
				if (!StringUtils.hasText(name)) {
					name = GENERATED_CONTEXT_HIERARCHY_LEVEL_PREFIX + hierarchyLevel;
				}

				// Encountered a new context hierarchy level?
				if (!map.containsKey(name)) {
					hierarchyLevel++;
					map.put(name, new ArrayList<>());
				}

				map.get(name).add(configAttributes);
			}
		}

		// Check for uniqueness
		Set<List<ContextConfigurationAttributes>> set = new HashSet<>(map.values());
		if (set.size() != map.size()) {
			String msg = String.format("The @ContextConfiguration elements configured via @ContextHierarchy in " +
					"test class [%s] and its superclasses must define unique contexts per hierarchy level.",
					testClass.getName());
			logger.error(msg);
			throw new IllegalStateException(msg);
		}

		return map;
	}
