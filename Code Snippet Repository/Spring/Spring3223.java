	@Test
	public void testSerializationWithManualConfiguration() throws Exception {
		InitDestroyAnnotationBeanPostProcessor bpp = new InitDestroyAnnotationBeanPostProcessor();
		bpp.setInitAnnotationType(PostConstruct.class);
		bpp.setDestroyAnnotationType(PreDestroy.class);
		InitDestroyAnnotationBeanPostProcessor bpp2 = (InitDestroyAnnotationBeanPostProcessor)
				SerializationTestUtils.serializeAndDeserialize(bpp);

		AnnotatedInitDestroyBean bean = new AnnotatedInitDestroyBean();
		bpp2.postProcessBeforeDestruction(bean, "annotatedBean");
		assertTrue(bean.destroyCalled);
	}
