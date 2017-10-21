	private void testSupportsStandardClasses() throws Exception {
		final StandardClasses standardClasses = new StandardClasses();
		ReflectionUtils.doWithMethods(StandardClasses.class, new ReflectionUtils.MethodCallback() {
			@Override
			public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
				Type returnType = method.getGenericReturnType();
				assertTrue("Jaxb2Marshaller does not support JAXBElement<" + method.getName().substring(13) + ">",
						marshaller.supports(returnType));
				try {
					// make sure the marshalling does not result in errors
					Object returnValue = method.invoke(standardClasses);
					marshaller.marshal(returnValue, new StreamResult(new ByteArrayOutputStream()));
				}
				catch (InvocationTargetException e) {
					fail(e.getMessage());
				}
			}
		}, new ReflectionUtils.MethodFilter() {
			@Override
			public boolean matches(Method method) {
				return method.getName().startsWith("standardClass");
			}
		});
	}
