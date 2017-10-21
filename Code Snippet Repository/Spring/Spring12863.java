	@Test
	public void uriTemplateVarAndRequestParam() throws Exception {
		request.addParameter("age", "35");

		Map<String, String> uriTemplateVars = new HashMap<>();
		uriTemplateVars.put("name", "nameValue");
		uriTemplateVars.put("age", "25");
		request.setAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, uriTemplateVars);

		TestBean target = new TestBean();
		WebDataBinder binder = new ExtendedServletRequestDataBinder(target, "");
		((ServletRequestDataBinder) binder).bind(request);

		assertEquals("nameValue", target.getName());
		assertEquals(35, target.getAge());
	}
