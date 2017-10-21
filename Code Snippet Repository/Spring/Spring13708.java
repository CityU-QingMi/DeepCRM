	@Test
	public void keepExistingContentType() throws Exception {
		FreeMarkerView fv = new FreeMarkerView();

		WebApplicationContext wac = mock(WebApplicationContext.class);
		MockServletContext sc = new MockServletContext();

		Map<String, FreeMarkerConfig> configs = new HashMap<>();
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setConfiguration(new TestConfiguration());
		configurer.setServletContext(sc);
		configs.put("configurer", configurer);
		given(wac.getBeansOfType(FreeMarkerConfig.class, true, false)).willReturn(configs);
		given(wac.getServletContext()).willReturn(sc);

		fv.setUrl("templateName");
		fv.setApplicationContext(wac);

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addPreferredLocale(Locale.US);
		request.setAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);
		request.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE, new AcceptHeaderLocaleResolver());
		HttpServletResponse response = new MockHttpServletResponse();
		response.setContentType("myContentType");

		Map<String, Object> model = new HashMap<>();
		model.put("myattr", "myvalue");
		fv.render(model, request, response);

		assertEquals("myContentType", response.getContentType());
	}
