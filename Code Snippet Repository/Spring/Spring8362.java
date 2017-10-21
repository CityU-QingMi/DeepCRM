	@Test
	public void cookieHeaderSet() throws Exception {
		HttpCookie foo11 = new HttpCookie("foo1", "bar1");
		HttpCookie foo12 = new HttpCookie("foo1", "bar2");
		HttpCookie foo21 = new HttpCookie("foo2", "baz1");
		HttpCookie foo22 = new HttpCookie("foo2", "baz2");

		MockServerHttpRequest request = MockServerHttpRequest.get("/")
				.cookie(foo11, foo12, foo21, foo22).build();

		assertEquals(Arrays.asList(foo11, foo12), request.getCookies().get("foo1"));
		assertEquals(Arrays.asList(foo21, foo22), request.getCookies().get("foo2"));
		assertEquals(Arrays.asList("foo1=bar1", "foo1=bar2", "foo2=baz1", "foo2=baz2"),
				request.getHeaders().get(HttpHeaders.COOKIE));
	}
