	@Before
	public void setup() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		bf.registerBeanDefinition("capitalizer", new RootBeanDefinition(Capitalizer.class));
		instantiator = new SpringHandlerInstantiator(bf);
		objectMapper = Jackson2ObjectMapperBuilder.json().handlerInstantiator(instantiator).build();
	}
