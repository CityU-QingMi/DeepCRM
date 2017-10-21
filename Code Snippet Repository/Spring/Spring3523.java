	@Test
	public void repro() {
		ConfigurableApplicationContext parent = new GenericApplicationContext();
		parent.refresh();

		AnnotationConfigApplicationContext child = new AnnotationConfigApplicationContext();
		child.setParent(parent);
		child.refresh();

		ConfigurableEnvironment env = child.getBean(ConfigurableEnvironment.class);
		assertThat("unknown env", env, anyOf(
				sameInstance(parent.getEnvironment()),
				sameInstance(child.getEnvironment())));
		assertThat("expected child ctx env", env, sameInstance(child.getEnvironment()));

		child.close();
		parent.close();
	}
