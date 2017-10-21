	protected boolean checkResource(Resource resource, Resource location) throws IOException {
		if (isResourceUnderLocation(resource, location)) {
			return true;
		}
		Resource[] allowedLocations = getAllowedLocations();
		if (allowedLocations != null) {
			for (Resource current : allowedLocations) {
				if (isResourceUnderLocation(resource, current)) {
					return true;
				}
			}
		}
		return false;
	}
