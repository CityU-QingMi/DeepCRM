	@Override
	@Test
	public void httpMethods() throws Exception {
		super.httpMethods();
		try {
			assertHttpMethod("patch", HttpMethod.PATCH);
		}
		catch (ProtocolException ex) {
			// Currently HttpURLConnection does not support HTTP PATCH
		}
	}
