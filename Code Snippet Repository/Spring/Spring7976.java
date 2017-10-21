	@Test
	public void testPrivateVendorSpecificPersistenceContextField() {
		GenericApplicationContext gac = new GenericApplicationContext();
		gac.getDefaultListableBeanFactory().registerSingleton("entityManagerFactory", mockEmf);
		gac.registerBeanDefinition("annotationProcessor",
				new RootBeanDefinition(PersistenceAnnotationBeanPostProcessor.class));
		gac.registerBeanDefinition(DefaultVendorSpecificPrivatePersistenceContextField.class.getName(),
				new RootBeanDefinition(DefaultVendorSpecificPrivatePersistenceContextField.class));
		gac.refresh();

		DefaultVendorSpecificPrivatePersistenceContextField bean = (DefaultVendorSpecificPrivatePersistenceContextField)
				gac.getBean(DefaultVendorSpecificPrivatePersistenceContextField.class.getName());
		assertNotNull(bean.em);
	}
