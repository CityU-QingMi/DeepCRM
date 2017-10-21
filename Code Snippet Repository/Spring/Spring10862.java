	@Test
	public void missingRequestParamEmptyValueNotRequired() throws Exception {
		WebDataBinder binder = new WebRequestDataBinder(null);
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

		WebDataBinderFactory binderFactory = mock(WebDataBinderFactory.class);
		given(binderFactory.createBinder(webRequest, null, "name")).willReturn(binder);

		this.request.addParameter("name", "");

		MethodParameter param = this.testMethod.annot(requestParam().notRequired()).arg(String.class);
		Object arg = resolver.resolveArgument(param, null, webRequest, binderFactory);
		assertNull(arg);
	}
