	@Override
	@Nullable
	public BeanDefinition parse(Element element, ParserContext parserContext) {

		Map<String, CorsConfiguration> corsConfigurations = new LinkedHashMap<>();
		List<Element> mappings = DomUtils.getChildElementsByTagName(element, "mapping");

		if (mappings.isEmpty()) {
			CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
			corsConfigurations.put("/**", config);
		}
		else {
			for (Element mapping : mappings) {
				CorsConfiguration config = new CorsConfiguration();
				if (mapping.hasAttribute("allowed-origins")) {
					String[] allowedOrigins = StringUtils.tokenizeToStringArray(mapping.getAttribute("allowed-origins"), ",");
					config.setAllowedOrigins(Arrays.asList(allowedOrigins));
				}
				if (mapping.hasAttribute("allowed-methods")) {
					String[] allowedMethods = StringUtils.tokenizeToStringArray(mapping.getAttribute("allowed-methods"), ",");
					config.setAllowedMethods(Arrays.asList(allowedMethods));
				}
				if (mapping.hasAttribute("allowed-headers")) {
					String[] allowedHeaders = StringUtils.tokenizeToStringArray(mapping.getAttribute("allowed-headers"), ",");
					config.setAllowedHeaders(Arrays.asList(allowedHeaders));
				}
				if (mapping.hasAttribute("exposed-headers")) {
					String[] exposedHeaders = StringUtils.tokenizeToStringArray(mapping.getAttribute("exposed-headers"), ",");
					config.setExposedHeaders(Arrays.asList(exposedHeaders));
				}
				if (mapping.hasAttribute("allow-credentials")) {
					config.setAllowCredentials(Boolean.parseBoolean(mapping.getAttribute("allow-credentials")));
				}
				if (mapping.hasAttribute("max-age")) {
					config.setMaxAge(Long.parseLong(mapping.getAttribute("max-age")));
				}
				corsConfigurations.put(mapping.getAttribute("path"), config.applyPermitDefaultValues());
			}
		}

		MvcNamespaceUtils.registerCorsConfigurations(
				corsConfigurations, parserContext, parserContext.extractSource(element));
		return null;
	}
