	@Test
	public void nullOrEmpty() throws Exception {
		Method method = Empty.class.getMethod("getAge");

		AnnotationTransactionAttributeSource atas = new AnnotationTransactionAttributeSource();
		assertNull(atas.getTransactionAttribute(method, null));

		// Try again in case of caching
		assertNull(atas.getTransactionAttribute(method, null));
	}
