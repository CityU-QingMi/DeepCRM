	@Test
	public void extractUriTemplateVariables() throws Exception {
		assertMatches(parse("{hotel}"),"1");
		assertMatches(parse("/hotels/{hotel}"),"/hotels/1");
		checkCapture("/hotels/{hotel}", "/hotels/1", "hotel", "1");
		checkCapture("/h?tels/{hotel}", "/hotels/1", "hotel", "1");
		checkCapture("/hotels/{hotel}/bookings/{booking}", "/hotels/1/bookings/2", "hotel", "1", "booking", "2");
		checkCapture("/*/hotels/*/{hotel}", "/foo/hotels/bar/1", "hotel", "1");
		checkCapture("/{page}.html", "/42.html", "page", "42");
		checkNoMatch("/{var}","/");
		checkCapture("/{page}.*", "/42.html", "page", "42");
		checkCapture("/A-{B}-C", "/A-b-C", "B", "b");
		checkCapture("/{name}.{extension}", "/test.html", "name", "test", "extension", "html");

		assertNull(checkCapture("/{one}/", "//"));
		assertNull(checkCapture("", "/abc"));

		assertEquals(0, checkCapture("", "").getUriVariables().size());
		checkCapture("{id}", "99", "id", "99");
		checkCapture("/customer/{customerId}", "/customer/78", "customerId", "78");
		checkCapture("/customer/{customerId}/banana", "/customer/42/banana", "customerId",
				"42");
		checkCapture("{id}/{id2}", "99/98", "id", "99", "id2", "98");
		checkCapture("/foo/{bar}/boo/{baz}", "/foo/plum/boo/apple", "bar", "plum", "baz",
				"apple");
		checkCapture("/{bla}.*", "/testing.html", "bla", "testing");
		PathPattern.PathMatchInfo extracted = checkCapture("/abc", "/abc");
		assertEquals(0, extracted.getUriVariables().size());
		checkCapture("/{bla}/foo","/a/foo");
	}
