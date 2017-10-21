	@Nullable
	protected Resource getResource(String resourcePath, Resource location) throws IOException {
		Resource resource = location.createRelative(resourcePath);
		if (resource.exists() && resource.isReadable()) {
			if (checkResource(resource, location)) {
				return resource;
			}
			else if (logger.isTraceEnabled()) {
				Resource[] allowedLocations = getAllowedLocations();
				logger.trace("Resource path=\"" + resourcePath + "\" was successfully resolved " +
						"but resource=\"" +	resource.getURL() + "\" is neither under the " +
						"current location=\"" + location.getURL() + "\" nor under any of the " +
						"allowed locations=" + (allowedLocations != null ? Arrays.asList(allowedLocations) : "[]"));
			}
		}
		return null;
	}
