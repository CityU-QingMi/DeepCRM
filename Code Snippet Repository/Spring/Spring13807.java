		@Override
		public void addMapping(Element element, ManagedMap<String, Object> urlMap, ParserContext context) {
			String pathAttribute = element.getAttribute("path");
			List<String> mappings = Arrays.asList(StringUtils.tokenizeToStringArray(pathAttribute, ","));
			RuntimeBeanReference handlerReference = new RuntimeBeanReference(element.getAttribute("handler"));

			ConstructorArgumentValues cavs = new ConstructorArgumentValues();
			cavs.addIndexedArgumentValue(0, this.sockJsService, "SockJsService");
			cavs.addIndexedArgumentValue(1, handlerReference, "WebSocketHandler");

			RootBeanDefinition requestHandlerDef = new RootBeanDefinition(SockJsHttpRequestHandler.class, cavs, null);
			requestHandlerDef.setSource(context.extractSource(element));
			requestHandlerDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			String requestHandlerName = context.getReaderContext().registerWithGeneratedName(requestHandlerDef);
			RuntimeBeanReference requestHandlerRef = new RuntimeBeanReference(requestHandlerName);

			for (String mapping : mappings) {
				String pathPattern = (mapping.endsWith("/") ? mapping + "**" : mapping + "/**");
				urlMap.put(pathPattern, requestHandlerRef);
			}
		}
