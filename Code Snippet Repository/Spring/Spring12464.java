	@Override
	protected void renderMergedTemplateModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String url = getUrl();
		Assert.state(url != null, "'url' not set");

		Template template = getTemplate(url);
		template.make(model).writeTo(new BufferedWriter(response.getWriter()));
	}
