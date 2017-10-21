	@Test
	@SuppressWarnings("")
	public void defaultMerge() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(
				new ClassPathResource("NestedBeansElementAttributeRecursionTests-merge-context.xml", this.getClass()));

		TestBean topLevel = bf.getBean("topLevelConcreteTestBean", TestBean.class);
		// has the concrete child bean values
		assertThat((Iterable<String>) topLevel.getSomeList(), hasItems("charlie", "delta"));
		// but does not merge the parent values
		assertThat((Iterable<String>) topLevel.getSomeList(), not(hasItems("alpha", "bravo")));

		TestBean firstLevel = bf.getBean("firstLevelNestedTestBean", TestBean.class);
		// merges all values
		assertThat((Iterable<String>) firstLevel.getSomeList(),
				hasItems("charlie", "delta", "echo", "foxtrot"));

		TestBean secondLevel = bf.getBean("secondLevelNestedTestBean", TestBean.class);
		// merges all values
		assertThat((Iterable<String>)secondLevel.getSomeList(),
				hasItems("charlie", "delta", "echo", "foxtrot", "golf", "hotel"));
	}
