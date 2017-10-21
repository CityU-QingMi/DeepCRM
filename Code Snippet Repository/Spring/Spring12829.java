	@Test
	public void compareToWithHttpHeadMapping() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("HEAD");
		request.addHeader("Accept", "application/json");

		RequestMappingInfo noMethods = paths().build();
		RequestMappingInfo getMethod = paths().methods(GET).produces("application/json").build();
		RequestMappingInfo headMethod = paths().methods(HEAD).build();

		Comparator<RequestMappingInfo> comparator = (info, otherInfo) -> info.compareTo(otherInfo, request);

		List<RequestMappingInfo> list = asList(noMethods, getMethod, headMethod);
		Collections.shuffle(list);
		Collections.sort(list, comparator);

		assertEquals(headMethod, list.get(0));
		assertEquals(getMethod, list.get(1));
		assertEquals(noMethods, list.get(2));
	}
