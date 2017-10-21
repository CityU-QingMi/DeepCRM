	private void testSupportsPrimitives() {
		final Primitives primitives = new Primitives();
		ReflectionUtils.doWithMethods(Primitives.class, new ReflectionUtils.MethodCallback() {
			@Override
			public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
				Type returnType = method.getGenericReturnType();
				assertTrue("Jaxb2Marshaller does not support JAXBElement<" + method.getName().substring(9) + ">",
						marshaller.supports(returnType));
				try {
					// make sure the marshalling does not result in errors
					Object returnValue = method.invoke(primitives);
					marshaller.marshal(returnValue, new StreamResult(new ByteArrayOutputStream()));
				}
				catch (InvocationTargetException e) {
					fail(e.getMessage());
				}
			}
		}, new ReflectionUtils.MethodFilter() {
			@Override
			public boolean matches(Method method) {
				return method.getName().startsWith("primitive");
			}
		});
	}
