	@SuppressWarnings("")
	public void setDefaultUriVariables(Map<String, ?> defaultUriVariables) {
		UriTemplateHandler handler = this.syncTemplate.getUriTemplateHandler();
		if (handler instanceof DefaultUriBuilderFactory) {
			((DefaultUriBuilderFactory) handler).setDefaultUriVariables(defaultUriVariables);
		}
		else if (handler instanceof org.springframework.web.util.AbstractUriTemplateHandler) {
			((org.springframework.web.util.AbstractUriTemplateHandler) handler)
					.setDefaultUriVariables(defaultUriVariables);
		}
		else {
			throw new IllegalArgumentException(
					"This property is not supported with the configured UriTemplateHandler.");
		}
	}
