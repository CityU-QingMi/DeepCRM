	@Test
	public void annotatedGet() {
		CacheMethodDetails<CacheResult> methodDetails = create(CacheResult.class,
				SampleObject.class, "annotatedGet", Long.class, String.class);
		CacheResultOperation operation = createDefaultOperation(methodDetails);
		CacheInvocationParameter[] parameters = operation.getAllParameters(2L, "foo");

		Set<Annotation> firstParameterAnnotations = parameters[0].getAnnotations();
		assertEquals(1, firstParameterAnnotations.size());
		assertEquals(CacheKey.class, firstParameterAnnotations.iterator().next().annotationType());

		Set<Annotation> secondParameterAnnotations = parameters[1].getAnnotations();
		assertEquals(1, secondParameterAnnotations.size());
		assertEquals(Value.class, secondParameterAnnotations.iterator().next().annotationType());
	}
