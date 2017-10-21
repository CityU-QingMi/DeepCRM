		private static String[] calculateMatches(String propertyName, PropertyDescriptor[] propertyDescriptors, int maxDistance) {
			List<String> candidates = new ArrayList<>();
			for (PropertyDescriptor pd : propertyDescriptors) {
				if (pd.getWriteMethod() != null) {
					String possibleAlternative = pd.getName();
					if (calculateStringDistance(propertyName, possibleAlternative) <= maxDistance) {
						candidates.add(possibleAlternative);
					}
				}
			}
			Collections.sort(candidates);
			return StringUtils.toStringArray(candidates);
		}
