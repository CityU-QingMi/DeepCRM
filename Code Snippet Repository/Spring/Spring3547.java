	@Test
	public void multipleLocationsWithDefaultResolvedValue() throws Exception {
		// SPR-10619
		PropertySourcesPlaceholderConfigurer ppc = new PropertySourcesPlaceholderConfigurer();
		ClassPathResource doesNotHave = new ClassPathResource("test.properties", getClass());
		ClassPathResource setToTrue = new ClassPathResource("placeholder.properties", getClass());
		ppc.setLocations(doesNotHave, setToTrue);
		ppc.setIgnoreResourceNotFound(true);
		ppc.setIgnoreUnresolvablePlaceholders(true);
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.registerBeanDefinition("testBean",
				genericBeanDefinition(TestBean.class)
					.addPropertyValue("jedi", "${jedi:false}")
					.getBeanDefinition());
		ppc.postProcessBeanFactory(bf);
		assertThat(bf.getBean(TestBean.class).isJedi(), equalTo(true));
	}
