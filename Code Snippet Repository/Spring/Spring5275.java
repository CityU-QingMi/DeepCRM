	@Test
	public void covertNonOptionArgsToStringArrayAndList() {
		CommandLinePropertySource<?> ps =
			new SimpleCommandLinePropertySource("--o1=v1", "noa1", "--o2", "noa2");
		StandardEnvironment env = new StandardEnvironment();
		env.getPropertySources().addFirst(ps);

		String nonOptionArgs = env.getProperty("nonOptionArgs");
		assertThat(nonOptionArgs, equalTo("noa1,noa2"));

		String[] nonOptionArgsArray = env.getProperty("nonOptionArgs", String[].class);
		assertThat(nonOptionArgsArray[0], equalTo("noa1"));
		assertThat(nonOptionArgsArray[1], equalTo("noa2"));

		@SuppressWarnings("unchecked")
		List<String> nonOptionArgsList = env.getProperty("nonOptionArgs", List.class);
		assertThat(nonOptionArgsList.get(0), equalTo("noa1"));
		assertThat(nonOptionArgsList.get(1), equalTo("noa2"));
	}
