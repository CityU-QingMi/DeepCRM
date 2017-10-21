	private RuntimeBeanReference registerMessageConverter(
			Element element, ParserContext context, @Nullable Object source) {

		Element convertersElement = DomUtils.getChildElementByTagName(element, "message-converters");
		ManagedList<? super Object> converters = new ManagedList<>();
		if (convertersElement != null) {
			converters.setSource(source);
			for (Element beanElement : DomUtils.getChildElementsByTagName(convertersElement, "bean", "ref")) {
				Object object = context.getDelegate().parsePropertySubElement(beanElement, null);
				converters.add(object);
			}
		}
		if (convertersElement == null || Boolean.valueOf(convertersElement.getAttribute("register-defaults"))) {
			converters.setSource(source);
			converters.add(new RootBeanDefinition(StringMessageConverter.class));
			converters.add(new RootBeanDefinition(ByteArrayMessageConverter.class));
			if (jackson2Present) {
				RootBeanDefinition jacksonConverterDef = new RootBeanDefinition(MappingJackson2MessageConverter.class);
				RootBeanDefinition resolverDef = new RootBeanDefinition(DefaultContentTypeResolver.class);
				resolverDef.getPropertyValues().add("defaultMimeType", MimeTypeUtils.APPLICATION_JSON);
				jacksonConverterDef.getPropertyValues().add("contentTypeResolver", resolverDef);
				// Use Jackson factory in order to have JSR-310 and Joda-Time modules registered automatically
				GenericBeanDefinition jacksonFactoryDef = new GenericBeanDefinition();
				jacksonFactoryDef.setBeanClass(Jackson2ObjectMapperFactoryBean.class);
				jacksonFactoryDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
				jacksonFactoryDef.setSource(source);
				jacksonConverterDef.getPropertyValues().add("objectMapper", jacksonFactoryDef);
				converters.add(jacksonConverterDef);
			}
		}
		ConstructorArgumentValues cavs = new ConstructorArgumentValues();
		cavs.addIndexedArgumentValue(0, converters);
		RootBeanDefinition messageConverterDef = new RootBeanDefinition(CompositeMessageConverter.class, cavs, null);
		String name = MESSAGE_CONVERTER_BEAN_NAME;
		registerBeanDefByName(name, messageConverterDef, context, source);
		return new RuntimeBeanReference(name);
	}
