	public SpringTemplateLoader(ResourceLoader resourceLoader, String templateLoaderPath) {
		this.resourceLoader = resourceLoader;
		if (!templateLoaderPath.endsWith("/")) {
			templateLoaderPath += "/";
		}
		this.templateLoaderPath = templateLoaderPath;
		if (logger.isInfoEnabled()) {
			logger.info("SpringTemplateLoader for FreeMarker: using resource loader [" + this.resourceLoader +
					"] and template loader path [" + this.templateLoaderPath + "]");
		}
	}
