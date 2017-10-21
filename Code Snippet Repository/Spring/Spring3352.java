	@Test
	public void testCustomPropertiesWithGenericContext() throws IOException {
		GenericApplicationContext context = new GenericApplicationContext();
		new XmlBeanDefinitionReader(context).loadBeanDefinitions(
				new ClassPathResource("AutowiredConfigurationTests-custom.xml", AutowiredConfigurationTests.class));
		context.refresh();

		TestBean testBean = context.getBean("testBean", TestBean.class);
		assertThat(testBean.getName(), equalTo("localhost"));
		assertThat(testBean.getAge(), equalTo(contentLength()));
	}
