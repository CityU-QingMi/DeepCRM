	@Test
	public void missingRequestParamEmptyValueConvertedToNull() throws Exception {
		WebDataBinder binder = new WebRequestDataBinder(null);
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

		WebDataBinderFactory binderFactory = mock(WebDataBinderFactory.class);
		given(binderFactory.createBinder(webRequest, null, "stringNotAnnot")).willReturn(binder);

		this.request.addParameter("stringNotAnnot", "");

		MethodParameter param = this.testMethod.annotNotPresent(RequestParam.class).arg(String.class);
		Object arg = resolver.resolveArgument(param, null, webRequest, binderFactory);
		assertNull(arg);
	}
