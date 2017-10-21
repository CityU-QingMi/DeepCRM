	@Override
	protected void handleMatch(SimpMessageMappingInfo mapping, HandlerMethod handlerMethod,
			String lookupDestination, Message<?> message) {

		Set<String> patterns = mapping.getDestinationConditions().getPatterns();
		if (!CollectionUtils.isEmpty(patterns)) {
			String pattern = patterns.iterator().next();
			Map<String, String> vars = getPathMatcher().extractUriTemplateVariables(pattern, lookupDestination);
			if (!CollectionUtils.isEmpty(vars)) {
				MessageHeaderAccessor mha = MessageHeaderAccessor.getAccessor(message, MessageHeaderAccessor.class);
				Assert.state(mha != null && mha.isMutable(), "Mutable MessageHeaderAccessor required");
				mha.setHeader(DestinationVariableMethodArgumentResolver.DESTINATION_TEMPLATE_VARIABLES_HEADER, vars);
			}
		}

		try {
			SimpAttributesContextHolder.setAttributesFromMessage(message);
			super.handleMatch(mapping, handlerMethod, lookupDestination, message);
		}
		finally {
			SimpAttributesContextHolder.resetAttributes();
		}
	}
