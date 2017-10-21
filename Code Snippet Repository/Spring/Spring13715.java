	@Test
	public void missingGroovyMarkupConfig() throws Exception {
		GroovyMarkupView view = new GroovyMarkupView();
		given(this.webAppContext.getBeansOfType(GroovyMarkupConfig.class, true, false))
				.willReturn(new HashMap<>());

		view.setUrl("sampleView");
		try {
			view.setApplicationContext(this.webAppContext);
			fail();
		}
		catch (ApplicationContextException ex) {
			assertTrue(ex.getMessage().contains("GroovyMarkupConfig"));
		}
	}
