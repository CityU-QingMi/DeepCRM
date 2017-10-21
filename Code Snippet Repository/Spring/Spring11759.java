	@Test
	@SuppressWarnings("")
	public void validationErrorToMono() throws Exception {
		MethodParameter parameter = this.testMethod
				.annotNotPresent(ModelAttribute.class).arg(Mono.class, Foo.class);

		testValidationError(parameter,
				resolvedArgumentMono -> {
					Object value = resolvedArgumentMono.block(Duration.ofSeconds(5));
					assertNotNull(value);
					assertTrue(value instanceof Mono);
					return (Mono<?>) value;
				});
	}
