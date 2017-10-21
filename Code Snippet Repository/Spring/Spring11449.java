	@Test
	public void mutateDoesCopy() throws Exception {

		WebClient.Builder builder = WebClient.builder();
		builder.filter((request, next) -> next.exchange(request));
		builder.defaultHeader("foo", "bar");
		builder.defaultCookie("foo", "bar");
		WebClient client1 = builder.build();
		builder.filter((request, next) -> next.exchange(request));
		builder.defaultHeader("baz", "qux");
		builder.defaultCookie("baz", "qux");
		WebClient client2 = builder.build();

		client1.mutate().filters(filters -> assertEquals(1, filters.size()));
		client1.mutate().defaultHeaders(headers -> assertEquals(1, headers.size()));
		client1.mutate().defaultCookies(cookies -> assertEquals(1, cookies.size()));
		client2.mutate().filters(filters -> assertEquals(2, filters.size()));
		client2.mutate().defaultHeaders(headers -> assertEquals(2, headers.size()));
		client2.mutate().defaultCookies(cookies -> assertEquals(2, cookies.size()));
	}
