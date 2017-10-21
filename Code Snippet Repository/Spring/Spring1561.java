	@Test
	public void childBeanDefinition() {
		ChildBeanDefinition master = new ChildBeanDefinition("foo");
		ChildBeanDefinition equal = new ChildBeanDefinition("foo");
		ChildBeanDefinition notEqual = new ChildBeanDefinition("bar");
		ChildBeanDefinition subclass = new ChildBeanDefinition("foo") {
		};
		setBaseProperties(master);
		setBaseProperties(equal);
		setBaseProperties(notEqual);
		setBaseProperties(subclass);

		assertEqualsAndHashCodeContracts(master, equal, notEqual, subclass);
	}
