	static Map<String, List<String>> getAttributeAliasMap(@Nullable Class<? extends Annotation> annotationType) {
		if (annotationType == null) {
			return Collections.emptyMap();
		}

		Map<String, List<String>> map = attributeAliasesCache.get(annotationType);
		if (map != null) {
			return map;
		}

		map = new LinkedHashMap<>();
		for (Method attribute : getAttributeMethods(annotationType)) {
			List<String> aliasNames = getAttributeAliasNames(attribute);
			if (!aliasNames.isEmpty()) {
				map.put(attribute.getName(), aliasNames);
			}
		}

		attributeAliasesCache.put(annotationType, map);
		return map;
	}
