	private void testInvalidPath(HttpMethod httpMethod) throws Exception {
		this.request.setMethod(httpMethod.name());

		Resource location = new ClassPathResource("test/", getClass());
		this.handler.setLocations(Collections.singletonList(location));

		testInvalidPath(location, "../testsecret/secret.txt");
		testInvalidPath(location, "test/../../testsecret/secret.txt");
		testInvalidPath(location, ":/../../testsecret/secret.txt");

		location = new UrlResource(getClass().getResource("./test/"));
		this.handler.setLocations(Collections.singletonList(location));
		Resource secretResource = new UrlResource(getClass().getResource("testsecret/secret.txt"));
		String secretPath = secretResource.getURL().getPath();

		testInvalidPath(location, "file:" + secretPath);
		testInvalidPath(location, "/file:" + secretPath);
		testInvalidPath(location, "url:" + secretPath);
		testInvalidPath(location, "/url:" + secretPath);
		testInvalidPath(location, "/" + secretPath);
		testInvalidPath(location, "////../.." + secretPath);
		testInvalidPath(location, "/%2E%2E/testsecret/secret.txt");
		testInvalidPath(location, "/  " + secretPath);
		testInvalidPath(location, "url:" + secretPath);
	}
