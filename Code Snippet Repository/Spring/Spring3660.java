	@Test
	@SuppressWarnings("")
	public void formatFieldForValueInjectionUsingMetaAnnotations() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		RootBeanDefinition bd = new RootBeanDefinition(MetaValueBean.class);
		bd.setScope(BeanDefinition.SCOPE_PROTOTYPE);
		ac.registerBeanDefinition("valueBean", bd);
		ac.registerBeanDefinition("conversionService", new RootBeanDefinition(FormattingConversionServiceFactoryBean.class));
		ac.registerBeanDefinition("ppc", new RootBeanDefinition(PropertyPlaceholderConfigurer.class));
		ac.refresh();
		System.setProperty("myDate", "10-31-09");
		System.setProperty("myNumber", "99.99%");
		try {
			MetaValueBean valueBean = ac.getBean(MetaValueBean.class);
			assertEquals(new LocalDate(2009, 10, 31), new LocalDate(valueBean.date));
			assertEquals(Double.valueOf(0.9999), valueBean.number);
		}
		finally {
			System.clearProperty("myDate");
			System.clearProperty("myNumber");
		}
	}
