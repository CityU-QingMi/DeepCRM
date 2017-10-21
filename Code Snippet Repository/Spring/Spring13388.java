	@Test
	@SuppressWarnings("")
	public void requestContext() throws ServletException {
		PageContext pc = createPageContext();
		RequestContext rc = new RequestContext((HttpServletRequest) pc.getRequest(), pc.getServletContext());
		assertEquals("test message", rc.getMessage("test"));
		assertEquals("test message", rc.getMessage("test", (Object[]) null));
		assertEquals("test message", rc.getMessage("test", "default"));
		assertEquals("test message", rc.getMessage("test", (Object[]) null, "default"));
		assertEquals("test arg1 message arg2",
				rc.getMessage("testArgs", new String[] {"arg1", "arg2"}, "default"));
		assertEquals("test arg1 message arg2",
				rc.getMessage("testArgs", Arrays.asList(new String[] {"arg1", "arg2"}), "default"));
		assertEquals("default", rc.getMessage("testa", "default"));
		assertEquals("default", rc.getMessage("testa", (List) null, "default"));
		MessageSourceResolvable resolvable = new DefaultMessageSourceResolvable(new String[] {"test"});
		assertEquals("test message", rc.getMessage(resolvable));
	}
