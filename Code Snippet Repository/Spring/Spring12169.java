	public static MethodArgumentBuilder fromMappingName(@Nullable UriComponentsBuilder builder, String name) {
		RequestMappingInfoHandlerMapping handlerMapping = getRequestMappingInfoHandlerMapping();
		List<HandlerMethod> handlerMethods = handlerMapping.getHandlerMethodsForMappingName(name);
		if (handlerMethods == null) {
			throw new IllegalArgumentException("Mapping mappingName not found: " + name);
		}
		if (handlerMethods.size() != 1) {
			throw new IllegalArgumentException("No unique match for mapping mappingName " +
					name + ": " + handlerMethods);
		}
		HandlerMethod handlerMethod = handlerMethods.get(0);
		Class<?> controllerType = handlerMethod.getBeanType();
		Method method = handlerMethod.getMethod();
		return new MethodArgumentBuilder(builder, controllerType, method);
	}
