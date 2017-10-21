		public RequestMappingHandlerMapping getHandlerMapping() {
			RequestMappingHandlerMapping handlerMapping = handlerMappingFactory.get();
			handlerMapping.setEmbeddedValueResolver(new StaticStringValueResolver(placeholderValues));
			handlerMapping.setUseSuffixPatternMatch(useSuffixPatternMatch);
			handlerMapping.setUseTrailingSlashMatch(useTrailingSlashPatternMatch);
			handlerMapping.setOrder(0);
			handlerMapping.setInterceptors(getInterceptors());
			if (removeSemicolonContent != null) {
				handlerMapping.setRemoveSemicolonContent(removeSemicolonContent);
			}
			return handlerMapping;
		}
