	@Test
	public void compareTwoHttpMethodsOneParam() {
		RequestMappingInfo none = paths().build();
		RequestMappingInfo oneMethod = paths().methods(RequestMethod.GET).build();
		RequestMappingInfo oneMethodOneParam = paths().methods(RequestMethod.GET).params("foo").build();

		ServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/foo").build());
		Comparator<RequestMappingInfo> comparator = (info, otherInfo) -> info.compareTo(otherInfo, exchange);

		List<RequestMappingInfo> list = asList(none, oneMethod, oneMethodOneParam);
		Collections.shuffle(list);
		list.sort(comparator);

		assertEquals(oneMethodOneParam, list.get(0));
		assertEquals(oneMethod, list.get(1));
		assertEquals(none, list.get(2));
	}
