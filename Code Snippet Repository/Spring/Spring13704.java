	@Test
	public void testSpringMacroRequestContextAttributeUsed() {
		final String helperTool = "wrongType";

		FreeMarkerView fv = new FreeMarkerView() {
			@Override
			protected void processTemplate(Template template, SimpleHash model, HttpServletResponse response) {
				fail();
			}
		};
		fv.setUrl(TEMPLATE_FILE);
		fv.setApplicationContext(wac);
		fv.setExposeSpringMacroHelpers(true);

		Map<String, Object> model = new HashMap<>();
		model.put(FreeMarkerView.SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE, helperTool);

		try {
			fv.render(model, request, response);
		}
		catch (Exception ex) {
			assertTrue(ex instanceof ServletException);
			assertTrue(ex.getMessage().contains(FreeMarkerView.SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE));
		}
	}
