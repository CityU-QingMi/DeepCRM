	protected Template getTemplate(String viewUrl) throws Exception {
		Assert.state(this.engine != null, "No MarkupTemplateEngine set");
		try {
			return this.engine.createTemplateByPath(viewUrl);
		}
		catch (ClassNotFoundException ex) {
			Throwable cause = (ex.getCause() != null ? ex.getCause() : ex);
			throw new NestedServletException(
					"Could not find class while rendering Groovy Markup view with name '" +
					getUrl() + "': " + ex.getMessage() + "'", cause);
		}
	}
