	@Test
	@SuppressWarnings("")
	public void requestContext() throws ServletException {
		PageContext pc = createPageContext();
		RequestContext rc = new RequestContext((HttpServletRequest) pc.getRequest());
		assertEquals("theme test message", rc.getThemeMessage("themetest"));
		assertEquals("theme test message", rc.getThemeMessage("themetest", (String[]) null));
		assertEquals("theme test message", rc.getThemeMessage("themetest", "default"));
		assertEquals("theme test message", rc.getThemeMessage("themetest", (Object[]) null, "default"));
		assertEquals("theme test message arg1",
				rc.getThemeMessage("themetestArgs", new String[] {"arg1"}));
		assertEquals("theme test message arg1",
				rc.getThemeMessage("themetestArgs", Arrays.asList(new String[] {"arg1"})));
		assertEquals("default", rc.getThemeMessage("themetesta", "default"));
		assertEquals("default", rc.getThemeMessage("themetesta", (List) null, "default"));
		MessageSourceResolvable resolvable = new DefaultMessageSourceResolvable(new String[] {"themetest"});
		assertEquals("theme test message", rc.getThemeMessage(resolvable));
	}
