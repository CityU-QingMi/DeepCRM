	@Override
	protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(AnnotationMBeanExporter.class);

		// Mark as infrastructure bean and attach source location.
		builder.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		builder.getRawBeanDefinition().setSource(parserContext.extractSource(element));

		String defaultDomain = element.getAttribute(DEFAULT_DOMAIN_ATTRIBUTE);
		if (StringUtils.hasText(defaultDomain)) {
			builder.addPropertyValue("defaultDomain", defaultDomain);
		}

		String serverBeanName = element.getAttribute(SERVER_ATTRIBUTE);
		if (StringUtils.hasText(serverBeanName)) {
			builder.addPropertyReference("server", serverBeanName);
		}
		else {
			AbstractBeanDefinition specialServer = MBeanServerBeanDefinitionParser.findServerForSpecialEnvironment();
			if (specialServer != null) {
				builder.addPropertyValue("server", specialServer);
			}
		}

		String registration = element.getAttribute(REGISTRATION_ATTRIBUTE);
		RegistrationPolicy registrationPolicy = RegistrationPolicy.FAIL_ON_EXISTING;
		if (REGISTRATION_IGNORE_EXISTING.equals(registration)) {
			registrationPolicy = RegistrationPolicy.IGNORE_EXISTING;
		}
		else if (REGISTRATION_REPLACE_EXISTING.equals(registration)) {
			registrationPolicy = RegistrationPolicy.REPLACE_EXISTING;
		}
		builder.addPropertyValue("registrationPolicy", registrationPolicy);

		return builder.getBeanDefinition();
	}
