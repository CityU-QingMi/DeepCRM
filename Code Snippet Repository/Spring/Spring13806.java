		@Override
		public void addMapping(Element element, ManagedMap<String, Object> urlMap, ParserContext context) {
			String pathAttribute = element.getAttribute("path");
			List<String> mappings = Arrays.asList(StringUtils.tokenizeToStringArray(pathAttribute, ","));
			RuntimeBeanReference handlerReference = new RuntimeBeanReference(element.getAttribute("handler"));

			ConstructorArgumentValues cavs = new ConstructorArgumentValues();
			cavs.addIndexedArgumentValue(0, handlerReference);
			cavs.addIndexedArgumentValue(1, this.handshakeHandlerReference);
			RootBeanDefinition requestHandlerDef = new RootBeanDefinition(WebSocketHttpRequestHandler.class, cavs, null);
			requestHandlerDef.setSource(context.extractSource(element));
			requestHandlerDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			requestHandlerDef.getPropertyValues().add("handshakeInterceptors", this.interceptorsList);
			String requestHandlerName = context.getReaderContext().registerWithGeneratedName(requestHandlerDef);
			RuntimeBeanReference requestHandlerRef = new RuntimeBeanReference(requestHandlerName);

			for (String mapping : mappings) {
				urlMap.put(mapping, requestHandlerRef);
			}
		}
