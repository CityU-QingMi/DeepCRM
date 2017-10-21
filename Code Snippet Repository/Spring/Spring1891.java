	@Override
	@Nullable
	public URL getResource(String name) {
		Assert.state(this.resourceLoader != null, "ResourceLoaderClassLoadHelper not initialized");
		Resource resource = this.resourceLoader.getResource(name);
		if (resource.exists()) {
			try {
				return resource.getURL();
			}
			catch (IOException ex) {
				if (logger.isWarnEnabled()) {
					logger.warn("Could not load " + resource);
				}
				return null;
			}
		}
		else {
			return getClassLoader().getResource(name);
		}
	}
