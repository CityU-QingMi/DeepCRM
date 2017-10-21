	private void testInvalidPath(HttpMethod httpMethod) throws Exception {
		Resource location = new ClassPathResource("test/", getClass());
		this.handler.setLocations(Collections.singletonList(location));

		testInvalidPath(httpMethod, "../testsecret/secret.txt", location);
		testInvalidPath(httpMethod, "test/../../testsecret/secret.txt", location);
		testInvalidPath(httpMethod, ":/../../testsecret/secret.txt", location);

		location = new UrlResource(getClass().getResource("./test/"));
		this.handler.setLocations(Collections.singletonList(location));
		Resource secretResource = new UrlResource(getClass().getResource("testsecret/secret.txt"));
		String secretPath = secretResource.getURL().getPath();

		testInvalidPath(httpMethod, "file:" + secretPath, location);
		testInvalidPath(httpMethod, "/file:" + secretPath, location);
		testInvalidPath(httpMethod, "url:" + secretPath, location);
		testInvalidPath(httpMethod, "/url:" + secretPath, location);
		testInvalidPath(httpMethod, "////../.." + secretPath, location);
		testInvalidPath(httpMethod, "/%2E%2E/testsecret/secret.txt", location);
		testInvalidPath(httpMethod, "url:" + secretPath, location);

		// The following tests fail with a MalformedURLException on Windows
		// testInvalidPath(location, "/" + secretPath);
		// testInvalidPath(location, "/  " + secretPath);
	}
