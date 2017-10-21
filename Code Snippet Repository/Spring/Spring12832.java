	@Test
	public void resolveOptional() throws Exception {
		WebDataBinder dataBinder = new WebRequestDataBinder(null);
		dataBinder.setConversionService(new DefaultConversionService());
		WebDataBinderFactory factory = mock(WebDataBinderFactory.class);
		given(factory.createBinder(this.webRequest, null, "foo")).willReturn(dataBinder);

		MethodParameter param = initMethodParameter(3);
		Object actual = testResolveArgument(param, factory);
		assertNotNull(actual);
		assertEquals(Optional.class, actual.getClass());
		assertFalse(((Optional) actual).isPresent());

		Foo foo = new Foo();
		this.webRequest.setAttribute("foo", foo, getScope());

		actual = testResolveArgument(param, factory);
		assertNotNull(actual);
		assertEquals(Optional.class, actual.getClass());
		assertTrue(((Optional) actual).isPresent());
		assertSame(foo, ((Optional) actual).get());
	}
