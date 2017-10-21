	@Test
	public void testExposeSpringMacroHelpers() throws Exception {
		FreeMarkerView fv = new FreeMarkerView() {
			@Override
			@SuppressWarnings("rawtypes")
			protected void processTemplate(Template template, SimpleHash fmModel, HttpServletResponse response)
					throws TemplateException {
				Map model = fmModel.toMap();
				assertTrue(model.get(FreeMarkerView.SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE) instanceof RequestContext);
				RequestContext rc = (RequestContext) model.get(FreeMarkerView.SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE);
				BindStatus status = rc.getBindStatus("tb.name");
				assertEquals("name", status.getExpression());
				assertEquals("juergen", status.getValue());
			}
		};
		fv.setUrl(TEMPLATE_FILE);
		fv.setApplicationContext(wac);
		fv.setExposeSpringMacroHelpers(true);

		Map<String, Object> model = new HashMap<>();
		model.put("tb", new TestBean("juergen", 99));
		fv.render(model, request, response);
	}
