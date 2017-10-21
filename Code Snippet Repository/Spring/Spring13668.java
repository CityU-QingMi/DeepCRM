	@Test
	public void remoteHost() throws Exception {
		RedirectView rv = new RedirectView();

		assertFalse(rv.isRemoteHost("http://url.somewhere.com"));
		assertFalse(rv.isRemoteHost("/path"));
		assertFalse(rv.isRemoteHost("http://url.somewhereelse.com"));

		rv.setHosts(new String[] {"url.somewhere.com"});

		assertFalse(rv.isRemoteHost("http://url.somewhere.com"));
		assertFalse(rv.isRemoteHost("/path"));
		assertTrue(rv.isRemoteHost("http://url.somewhereelse.com"));

	}
