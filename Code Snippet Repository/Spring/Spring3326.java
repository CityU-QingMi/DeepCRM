	@Test
	public void orderingDoesntReplaceExisting() throws Exception {
		// SPR-12198: mySource should 'win' as it was registered manually
		AnnotationConfigApplicationContext ctxWithoutName = new AnnotationConfigApplicationContext();
		MapPropertySource mySource = new MapPropertySource("mine", Collections.singletonMap("testbean.name", "myTestBean"));
		ctxWithoutName.getEnvironment().getPropertySources().addLast(mySource);
		ctxWithoutName.register(ConfigWithFourResourceLocations.class);
		ctxWithoutName.refresh();
		assertThat(ctxWithoutName.getEnvironment().getProperty("testbean.name"), equalTo("myTestBean"));

	}
