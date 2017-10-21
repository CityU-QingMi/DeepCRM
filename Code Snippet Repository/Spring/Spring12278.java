	@Nullable
	private Resource getResource(String resourcePath, List<? extends Resource> locations) {
		for (Resource location : locations) {
			try {
				if (logger.isTraceEnabled()) {
					logger.trace("Checking location: " + location);
				}
				Resource resource = getResource(resourcePath, location);
				if (resource != null) {
					if (logger.isTraceEnabled()) {
						logger.trace("Found match: " + resource);
					}
					return resource;
				}
				else if (logger.isTraceEnabled()) {
					logger.trace("No match for location: " + location);
				}
			}
			catch (IOException ex) {
				logger.trace("Failure checking for relative resource - trying next location", ex);
			}
		}
		return null;
	}
