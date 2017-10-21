	@Test
	public void rootBeanDefinition() {
		RootBeanDefinition master = new RootBeanDefinition(TestBean.class);
		RootBeanDefinition equal = new RootBeanDefinition(TestBean.class);
		RootBeanDefinition notEqual = new RootBeanDefinition(String.class);
		RootBeanDefinition subclass = new RootBeanDefinition(TestBean.class) {
		};
		setBaseProperties(master);
		setBaseProperties(equal);
		setBaseProperties(notEqual);
		setBaseProperties(subclass);

		assertEqualsAndHashCodeContracts(master, equal, notEqual, subclass);
	}
