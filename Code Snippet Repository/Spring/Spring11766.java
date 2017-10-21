	@Test
	public void createAndBindToSingle() throws Exception {
		MethodParameter parameter = this.testMethod
				.annotPresent(ModelAttribute.class).arg(Single.class, Foo.class);

		testBindFoo("fooSingle", parameter, single -> {
			assertTrue(single.getClass().getName(), single instanceof Single);
			Object value = ((Single<?>) single).toBlocking().value();
			assertEquals(Foo.class, value.getClass());
			return (Foo) value;
		});
	}
