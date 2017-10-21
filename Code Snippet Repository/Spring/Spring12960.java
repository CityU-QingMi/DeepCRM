	private RequestMappingInfo assertComposedAnnotationMapping(String methodName, String path,
			RequestMethod requestMethod) throws Exception {

		Class<?> clazz = ComposedAnnotationController.class;
		Method method = clazz.getMethod(methodName);
		RequestMappingInfo info = this.handlerMapping.getMappingForMethod(method, clazz);

		assertNotNull(info);

		Set<String> paths = info.getPatternsCondition().getPatterns();
		assertEquals(1, paths.size());
		assertEquals(path, paths.iterator().next());

		Set<RequestMethod> methods = info.getMethodsCondition().getMethods();
		assertEquals(1, methods.size());
		assertEquals(requestMethod, methods.iterator().next());

		return info;
	}
