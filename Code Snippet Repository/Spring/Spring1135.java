	@Test
	public void testGetBeanNamesForAnnotationWithOverride() throws Exception {
		AnnotatedBean annotatedBean = new AnnotatedBean();
		this.listableBeanFactory.registerSingleton("anotherAnnotatedBean", annotatedBean);
		List<String> names = Arrays.asList(
				BeanFactoryUtils.beanNamesForAnnotationIncludingAncestors(this.listableBeanFactory, TestAnnotation.class));
		assertEquals(2, names.size());
		assertTrue(names.contains("annotatedBean"));
		assertTrue(names.contains("anotherAnnotatedBean"));
	}
