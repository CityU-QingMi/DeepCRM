	@Test
	public void remoteHost() {
		RedirectView view = new RedirectView("");

		assertFalse(view.isRemoteHost("http://url.somewhere.com"));
		assertFalse(view.isRemoteHost("/path"));
		assertFalse(view.isRemoteHost("http://url.somewhereelse.com"));

		view.setHosts("url.somewhere.com");

		assertFalse(view.isRemoteHost("http://url.somewhere.com"));
		assertFalse(view.isRemoteHost("/path"));
		assertTrue(view.isRemoteHost("http://url.somewhereelse.com"));
	}
