	@Test
	public void testCircularTypeReference() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("bean1", new RootBeanDefinition(StockServiceImpl.class));
		bf.registerBeanDefinition("bean2", new RootBeanDefinition(StockMovementDaoImpl.class));
		bf.registerBeanDefinition("bean3", new RootBeanDefinition(StockMovementImpl.class));
		bf.registerBeanDefinition("bean4", new RootBeanDefinition(StockMovementInstructionImpl.class));

		StockServiceImpl service = bf.getBean(StockServiceImpl.class);
		assertSame(bf.getBean(StockMovementDaoImpl.class), service.stockMovementDao);
	}
