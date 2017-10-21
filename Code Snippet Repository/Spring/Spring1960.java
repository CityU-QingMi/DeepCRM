	@Test
	public void simple() {
		O operation = createSimpleOperation();
		assertEquals("Wrong cache name", "simpleCache", operation.getCacheName());
		assertEquals("Unexpected number of annotation on " + operation.getMethod(),
				1, operation.getAnnotations().size());
		assertEquals("Wrong method annotation", operation.getCacheAnnotation(),
				operation.getAnnotations().iterator().next());

		assertNotNull("cache resolver should be set", operation.getCacheResolver());
	}
