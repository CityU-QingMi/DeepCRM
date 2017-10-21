	@Test
	public void defaultLazyInit() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(
				new ClassPathResource("NestedBeansElementAttributeRecursionTests-lazy-context.xml", this.getClass()));

		BeanDefinition foo = bf.getBeanDefinition("foo");
		BeanDefinition bar = bf.getBeanDefinition("bar");
		BeanDefinition baz = bf.getBeanDefinition("baz");
		BeanDefinition biz = bf.getBeanDefinition("biz");
		BeanDefinition buz = bf.getBeanDefinition("buz");

		assertThat(foo.isLazyInit(), is(false));
		assertThat(bar.isLazyInit(), is(true));
		assertThat(baz.isLazyInit(), is(false));
		assertThat(biz.isLazyInit(), is(true));
		assertThat(buz.isLazyInit(), is(true));
	}
