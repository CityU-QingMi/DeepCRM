	@Test
	public void supportsAllDefaultHandlerExceptionResolverExceptionTypes() throws Exception {

		Class<ResponseEntityExceptionHandler> clazz = ResponseEntityExceptionHandler.class;
		Method handleExceptionMethod = clazz.getMethod("handleException", Exception.class, WebRequest.class);
		ExceptionHandler annotation = handleExceptionMethod.getAnnotation(ExceptionHandler.class);
		List<Class<?>> exceptionTypes = Arrays.asList(annotation.value());

		for (Method method : DefaultHandlerExceptionResolver.class.getDeclaredMethods()) {
			Class<?>[] paramTypes = method.getParameterTypes();
			if (method.getName().startsWith("handle") && (paramTypes.length == 4)) {
				String name = paramTypes[0].getSimpleName();
				assertTrue("@ExceptionHandler is missing " + name, exceptionTypes.contains(paramTypes[0]));
			}
		}
	}
