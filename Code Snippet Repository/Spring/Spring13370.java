	@Test
	public void environmentAccess() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("key.foo", "value.foo");
		GenericApplicationContext wac = (GenericApplicationContext)
		context.getRequest().getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		wac.getEnvironment().getPropertySources().addFirst(new MapPropertySource("mapSource", map));
		wac.getDefaultListableBeanFactory().registerSingleton("bean2", context.getRequest().getAttribute("bean"));
		tag.setExpression("@environment['key.foo']");
		int action = tag.doStartTag();
		assertEquals(Tag.EVAL_BODY_INCLUDE, action);
		action = tag.doEndTag();
		assertEquals(Tag.EVAL_PAGE, action);
		assertEquals("value.foo", ((MockHttpServletResponse) context.getResponse()).getContentAsString());
	}
