		private static String[] calculateMatches(final String propertyName, Class<?> beanClass, final int maxDistance) {
			final List<String> candidates = new ArrayList<>();
			ReflectionUtils.doWithFields(beanClass, field -> {
				String possibleAlternative = field.getName();
				if (calculateStringDistance(propertyName, possibleAlternative) <= maxDistance) {
					candidates.add(possibleAlternative);
				}
			});
			Collections.sort(candidates);
			return StringUtils.toStringArray(candidates);
		}
