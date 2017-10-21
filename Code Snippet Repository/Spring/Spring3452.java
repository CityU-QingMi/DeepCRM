	@Test
	public void prototypeCreationReevaluatesExpressions() {
		GenericApplicationContext ac = new GenericApplicationContext();
		AnnotationConfigUtils.registerAnnotationConfigProcessors(ac);
		GenericConversionService cs = new GenericConversionService();
		cs.addConverter(String.class, String.class, new Converter<String, String>() {
			@Override
			public String convert(String source) {
				return source.trim();
			}
		});
		ac.getBeanFactory().registerSingleton(GenericApplicationContext.CONVERSION_SERVICE_BEAN_NAME, cs);
		RootBeanDefinition rbd = new RootBeanDefinition(PrototypeTestBean.class);
		rbd.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		rbd.getPropertyValues().add("country", "#{systemProperties.country}");
		rbd.getPropertyValues().add("country2", new TypedStringValue("-#{systemProperties.country}-"));
		ac.registerBeanDefinition("test", rbd);
		ac.refresh();

		try {
			System.getProperties().put("name", "juergen1");
			System.getProperties().put("country", " UK1 ");
			PrototypeTestBean tb = (PrototypeTestBean) ac.getBean("test");
			assertEquals("juergen1", tb.getName());
			assertEquals("UK1", tb.getCountry());
			assertEquals("-UK1-", tb.getCountry2());

			System.getProperties().put("name", "juergen2");
			System.getProperties().put("country", "  UK2  ");
			tb = (PrototypeTestBean) ac.getBean("test");
			assertEquals("juergen2", tb.getName());
			assertEquals("UK2", tb.getCountry());
			assertEquals("-UK2-", tb.getCountry2());
		}
		finally {
			System.getProperties().remove("name");
			System.getProperties().remove("country");
		}
	}
