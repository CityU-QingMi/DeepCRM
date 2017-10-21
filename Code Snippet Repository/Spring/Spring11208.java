	protected void initAllowedLocations() {
		if (CollectionUtils.isEmpty(this.locations)) {
			if (logger.isWarnEnabled()) {
				logger.warn("Locations list is empty. No resources will be served unless a " +
						"custom ResourceResolver is configured as an alternative to PathResourceResolver.");
			}
			return;
		}
		for (int i = getResourceResolvers().size() - 1; i >= 0; i--) {
			if (getResourceResolvers().get(i) instanceof PathResourceResolver) {
				PathResourceResolver resolver = (PathResourceResolver) getResourceResolvers().get(i);
				if (ObjectUtils.isEmpty(resolver.getAllowedLocations())) {
					resolver.setAllowedLocations(getLocations().toArray(new Resource[getLocations().size()]));
				}
				break;
			}
		}
	}
