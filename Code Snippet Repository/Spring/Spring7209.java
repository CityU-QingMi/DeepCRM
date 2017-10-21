	protected String[] resolveEmbeddedValuesInDestinations(String[] destinations) {
		if (this.valueResolver == null) {
			return destinations;
		}
		String[] result = new String[destinations.length];
		for (int i = 0; i < destinations.length; i++) {
			result[i] = this.valueResolver.resolveStringValue(destinations[i]);
		}
		return result;
	}
