	@Test
	public void customMethodFilter() throws Exception {
		StandardEvaluationContext context = new StandardEvaluationContext();

		// Register a custom MethodResolver...
		List<MethodResolver> customResolvers = new ArrayList<>();
		customResolvers.add(new CustomMethodResolver());
		context.setMethodResolvers(customResolvers);

		// or simply...
		// context.setMethodResolvers(new ArrayList<MethodResolver>());

		// Register a custom MethodFilter...
		MethodFilter filter = new CustomMethodFilter();
		try {
			context.registerMethodFilter(String.class, filter);
			fail("should have failed");
		}
		catch (IllegalStateException ise) {
			assertEquals(
					"Method filter cannot be set as the reflective method resolver is not in use",
					ise.getMessage());
		}
	}
