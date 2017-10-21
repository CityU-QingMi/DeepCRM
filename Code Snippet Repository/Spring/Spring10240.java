	@Test
	public void pathSegmentParams() throws Exception {
		// basic
		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("colors", "red");
		params.add("colors", "blue");
		params.add("colors", "green");
		params.add("year", "2012");
		testPathSegment("cars;colors=red,blue,green;year=2012", "cars", params);

		// trailing semicolon
		params = new LinkedMultiValueMap<>();
		params.add("p", "1");
		testPathSegment("path;p=1;", "path", params);

		// params with spaces
		params = new LinkedMultiValueMap<>();
		params.add("param name", "param value");
		testPathSegment("path;param%20name=param%20value;%20", "path", params);

		// empty params
		params = new LinkedMultiValueMap<>();
		params.add("p", "1");
		testPathSegment("path;;;%20;%20;p=1;%20", "path", params);
	}
