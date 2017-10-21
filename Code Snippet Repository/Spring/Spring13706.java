	@Test
	public void noFreeMarkerConfig() throws Exception {
		FreeMarkerView fv = new FreeMarkerView();

		WebApplicationContext wac = mock(WebApplicationContext.class);
		given(wac.getBeansOfType(FreeMarkerConfig.class, true, false)).willReturn(new HashMap<>());
		given(wac.getServletContext()).willReturn(new MockServletContext());

		fv.setUrl("anythingButNull");

		exception.expect(ApplicationContextException.class);
		exception.expectMessage(containsString("FreeMarkerConfig"));
		fv.setApplicationContext(wac);
	}
