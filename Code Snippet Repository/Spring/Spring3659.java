	@Test
	@SuppressWarnings("")
	public void formatFieldForValueInjection() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.registerBeanDefinition("valueBean", new RootBeanDefinition(ValueBean.class));
		ac.registerBeanDefinition("conversionService", new RootBeanDefinition(FormattingConversionServiceFactoryBean.class));
		ac.refresh();
		ValueBean valueBean = ac.getBean(ValueBean.class);
		assertEquals(new LocalDate(2009, 10, 31), new LocalDate(valueBean.date));
	}
