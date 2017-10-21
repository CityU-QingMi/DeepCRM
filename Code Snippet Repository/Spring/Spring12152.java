	@Override
	@SuppressWarnings("")
	protected void addBindValues(MutablePropertyValues mpvs, ServletRequest request) {
		String attr = HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE;
		Map<String, String> uriVars = (Map<String, String>) request.getAttribute(attr);
		if (uriVars != null) {
			uriVars.forEach((name, value) -> {
				if (mpvs.contains(name)) {
					if (logger.isWarnEnabled()) {
						logger.warn("Skipping URI variable '" + name +
								"' since the request contains a bind value with the same name.");
					}
				}
				else {
					mpvs.addPropertyValue(name, value);
				}
			});
		}
	}
