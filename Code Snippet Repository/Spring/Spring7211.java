	@Override
	protected String getLookupDestination(@Nullable String destination) {
		if (destination == null) {
			return null;
		}
		if (CollectionUtils.isEmpty(getDestinationPrefixes())) {
			return destination;
		}
		for (String prefix : getDestinationPrefixes()) {
			if (destination.startsWith(prefix)) {
				if (this.slashPathSeparator) {
					return destination.substring(prefix.length() - 1);
				}
				else {
					return destination.substring(prefix.length());
				}
			}
		}
		return null;
	}
