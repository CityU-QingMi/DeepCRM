	@Test
	public void validationErrorToSingle() throws Exception {
		MethodParameter parameter = this.testMethod
				.annotPresent(ModelAttribute.class).arg(Single.class, Foo.class);

		testValidationError(parameter,
				resolvedArgumentMono -> {
					Object value = resolvedArgumentMono.block(Duration.ofSeconds(5));
					assertNotNull(value);
					assertTrue(value instanceof Single);
					return Mono.from(RxReactiveStreams.toPublisher((Single<?>) value));
				});
	}
