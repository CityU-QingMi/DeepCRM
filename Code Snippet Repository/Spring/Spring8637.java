	@Test
	public void cacheControl() throws Exception {

		CacheControl control = CacheControl.maxAge(1, TimeUnit.HOURS).noTransform();

		HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl(control.getHeaderValue());
		HeaderAssertions assertions = headerAssertions(headers);

		// Success
		assertions.cacheControl(control);

		try {
			assertions.cacheControl(CacheControl.noStore());
			fail("Wrong value expected");
		}
		catch (AssertionError error) {
			// Expected
		}
	}
