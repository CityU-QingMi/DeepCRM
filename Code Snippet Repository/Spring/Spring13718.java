	@Test
	public void renderI18nTemplate() throws Exception {
		Map<String, Object> model = new HashMap<>();
		model.put("name", "Spring");
		MockHttpServletResponse response = renderViewWithModel("i18n.tpl", model, Locale.FRANCE);
		assertEquals("<p>Bonjour Spring</p>", response.getContentAsString());

		response = renderViewWithModel("i18n.tpl", model, Locale.GERMANY);
		assertEquals("<p>Include German</p><p>Hallo Spring</p>", response.getContentAsString());

		response = renderViewWithModel("i18n.tpl", model, new Locale("es"));
		assertEquals("<p>Include Default</p><p>Hola Spring</p>", response.getContentAsString());
	}
