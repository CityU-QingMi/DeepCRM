	protected Mono<Resource> getResource(String resourcePath, Resource location) {
		try {
			Resource resource = location.createRelative(resourcePath);
			if (resource.exists() && resource.isReadable()) {
				if (checkResource(resource, location)) {
					if (logger.isTraceEnabled()) {
						logger.trace("Found match: " + resource);
					}
					return Mono.just(resource);
				}
				else if (logger.isTraceEnabled()) {
					Resource[] allowedLocations = getAllowedLocations();
					logger.trace("Resource path=\"" + resourcePath + "\" was successfully resolved " +
							"but resource=\"" + resource.getURL() + "\" is neither under the " +
							"current location=\"" + location.getURL() + "\" nor under any of the " +
							"allowed locations=" + (allowedLocations != null ? Arrays.asList(allowedLocations) : "[]"));
				}
			}
			else if (logger.isTraceEnabled()) {
				logger.trace("No match for location: " + location);
			}
			return Mono.empty();
		}
		catch (IOException ex) {
			if (logger.isTraceEnabled()) {
				logger.trace("Failure checking for relative resource under location + " + location, ex);
			}
			return Mono.error(ex);
		}
	}
