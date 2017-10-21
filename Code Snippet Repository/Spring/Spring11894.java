	@Test
	public void noFreeMarkerConfig() throws Exception {
		this.exception.expect(ApplicationContextException.class);
		this.exception.expectMessage("Must define a single FreeMarkerConfig bean");

		FreeMarkerView view = new FreeMarkerView();
		view.setApplicationContext(this.context);
		view.setUrl("anythingButNull");
		view.afterPropertiesSet();
	}
