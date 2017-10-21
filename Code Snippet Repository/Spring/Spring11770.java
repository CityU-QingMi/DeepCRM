	@Test
	public void bindExistingMonoToMono() throws Exception {
		Foo foo = new Foo();
		foo.setName("Jim");
		String modelKey = "fooMono";
		this.bindContext.getModel().addAttribute(modelKey, Mono.just(foo));

		MethodParameter parameter = this.testMethod
				.annotNotPresent(ModelAttribute.class).arg(Mono.class, Foo.class);

		testBindFoo(modelKey, parameter, mono -> {
			assertTrue(mono.getClass().getName(), mono instanceof Mono);
			Object value = ((Mono<?>) mono).block(Duration.ofSeconds(5));
			assertEquals(Foo.class, value.getClass());
			return (Foo) value;
		});
	}
