	@Test
	public void createBinderTypeConversion() throws Exception {
		this.webRequest.getNativeRequest(MockHttpServletRequest.class).setParameter("requestParam", "22");
		this.argumentResolvers.addResolver(new RequestParamMethodArgumentResolver(null, false));

		WebDataBinderFactory factory = createFactory("initBinderTypeConversion", WebDataBinder.class, int.class);
		WebDataBinder dataBinder = factory.createBinder(this.webRequest, null, "foo");

		assertNotNull(dataBinder.getDisallowedFields());
		assertEquals("requestParam-22", dataBinder.getDisallowedFields()[0]);
	}
