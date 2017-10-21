	@Test
	public void createAndBindToMono() throws Exception {
		MethodParameter parameter = this.testMethod
				.annotNotPresent(ModelAttribute.class).arg(Mono.class, Foo.class);

		testBindFoo("fooMono", parameter, mono -> {
			assertTrue(mono.getClass().getName(), mono instanceof Mono);
			Object value = ((Mono<?>) mono).block(Duration.ofSeconds(5));
			assertEquals(Foo.class, value.getClass());
			return (Foo) value;
		});
	}
