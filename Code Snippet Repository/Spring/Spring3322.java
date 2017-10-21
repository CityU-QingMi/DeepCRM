	@Test
	public void withExplicitName() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ConfigWithExplicitName.class);
		ctx.refresh();
		assertTrue("property source p1 was not added",
				ctx.getEnvironment().getPropertySources().contains("p1"));
		assertThat(ctx.getBean(TestBean.class).getName(), equalTo("p1TestBean"));

		// assert that the property source was added last to the set of sources
		String name;
		MutablePropertySources sources = ctx.getEnvironment().getPropertySources();
		Iterator<org.springframework.core.env.PropertySource<?>> iterator = sources.iterator();
		do {
			name = iterator.next().getName();
		}
		while (iterator.hasNext());

		assertThat(name, is("p1"));
	}
