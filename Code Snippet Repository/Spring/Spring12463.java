	@Override
	public boolean checkResource(Locale locale) throws Exception {
		Assert.state(this.engine != null, "No MarkupTemplateEngine set");
		try {
			this.engine.resolveTemplate(getUrl());
		}
		catch (IOException ex) {
			return false;
		}
		return true;
	}
