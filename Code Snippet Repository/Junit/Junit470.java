	@Test
	void resolveMultipleArguments() {
		testMethodWith("multipleParameters", String.class, Integer.class, Double.class);
		register(ConfigurableParameterResolver.supportsAndResolvesTo(parameterContext -> {
			switch (parameterContext.getIndex()) {
				case 0:
					return "0";
				case 1:
					return 1;
				default:
					return 2.0;
			}
		}));

		invokeMethod();

		verify(instance).multipleParameters("0", 1, 2.0);
	}
