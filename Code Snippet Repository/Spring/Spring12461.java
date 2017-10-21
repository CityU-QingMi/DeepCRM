	protected URL resolveTemplate(ClassLoader classLoader, String templatePath) throws IOException {
		MarkupTemplateEngine.TemplateResource resource = MarkupTemplateEngine.TemplateResource.parse(templatePath);
		Locale locale = LocaleContextHolder.getLocale();
		URL url = classLoader.getResource(resource.withLocale(locale.toString().replace("-", "_")).toString());
		if (url == null) {
			url = classLoader.getResource(resource.withLocale(locale.getLanguage()).toString());
		}
		if (url == null) {
			url = classLoader.getResource(resource.withLocale(null).toString());
		}
		if (url == null) {
			throw new IOException("Unable to load template:" + templatePath);
		}
		return url;
	}
