	@Test
	public void testCustomConverter() {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		GenericConversionService conversionService = new DefaultConversionService();
		conversionService.addConverter(new Converter<String, Float>() {
			@Override
			public Float convert(String source) {
				try {
					NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
					return nf.parse(source).floatValue();
				}
				catch (ParseException ex) {
					throw new IllegalArgumentException(ex);
				}
			}
		});
		lbf.setConversionService(conversionService);
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("myFloat", "1,1");
		RootBeanDefinition bd = new RootBeanDefinition(TestBean.class);
		bd.setPropertyValues(pvs);
		lbf.registerBeanDefinition("testBean", bd);
		TestBean testBean = (TestBean) lbf.getBean("testBean");
		assertTrue(testBean.getMyFloat().floatValue() == 1.1f);
	}
