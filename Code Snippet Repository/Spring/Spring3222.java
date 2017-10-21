	@Test
	public void testSerialization() throws Exception {
		CommonAnnotationBeanPostProcessor bpp = new CommonAnnotationBeanPostProcessor();
		CommonAnnotationBeanPostProcessor bpp2 = (CommonAnnotationBeanPostProcessor)
				SerializationTestUtils.serializeAndDeserialize(bpp);

		AnnotatedInitDestroyBean bean = new AnnotatedInitDestroyBean();
		bpp2.postProcessBeforeDestruction(bean, "annotatedBean");
		assertTrue(bean.destroyCalled);
	}
