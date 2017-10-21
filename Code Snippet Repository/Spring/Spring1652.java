	@Test
	public void defaultAutowireCandidates() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(
				new ClassPathResource("NestedBeansElementAttributeRecursionTests-autowire-candidates-context.xml", this.getClass()));

		assertThat(bf.getBeanDefinition("fooService").isAutowireCandidate(), is(true));
		assertThat(bf.getBeanDefinition("fooRepository").isAutowireCandidate(), is(true));
		assertThat(bf.getBeanDefinition("other").isAutowireCandidate(), is(false));

		assertThat(bf.getBeanDefinition("barService").isAutowireCandidate(), is(true));
		assertThat(bf.getBeanDefinition("fooController").isAutowireCandidate(), is(false));

		assertThat(bf.getBeanDefinition("bizRepository").isAutowireCandidate(), is(true));
		assertThat(bf.getBeanDefinition("bizService").isAutowireCandidate(), is(false));

		assertThat(bf.getBeanDefinition("bazService").isAutowireCandidate(), is(true));
		assertThat(bf.getBeanDefinition("random").isAutowireCandidate(), is(false));
		assertThat(bf.getBeanDefinition("fooComponent").isAutowireCandidate(), is(false));
		assertThat(bf.getBeanDefinition("fRepository").isAutowireCandidate(), is(false));

		assertThat(bf.getBeanDefinition("aComponent").isAutowireCandidate(), is(true));
		assertThat(bf.getBeanDefinition("someService").isAutowireCandidate(), is(false));
	}
