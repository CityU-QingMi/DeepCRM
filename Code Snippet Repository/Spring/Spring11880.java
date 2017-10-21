	@Test
	public void propagateQueryParams() throws Exception {
		RedirectView view = new RedirectView("http://url.somewhere.com?foo=bar#bazz");
		view.setPropagateQuery(true);
		MockServerHttpRequest request = MockServerHttpRequest.get("http://url.somewhere.com?a=b&c=d").build();
		this.exchange = MockServerWebExchange.from(request);
		view.render(new HashMap<>(), MediaType.TEXT_HTML, this.exchange).block();
		assertEquals(HttpStatus.SEE_OTHER, this.exchange.getResponse().getStatusCode());
		assertEquals(URI.create("http://url.somewhere.com?foo=bar&a=b&c=d#bazz"),
				this.exchange.getResponse().getHeaders().getLocation());
	}
