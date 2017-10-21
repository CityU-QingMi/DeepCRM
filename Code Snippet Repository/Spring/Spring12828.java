	@Test
	public void compareToWithImpicitVsExplicitHttpMethodDeclaration() {
		RequestMappingInfo noMethods = paths().build();
		RequestMappingInfo oneMethod = paths().methods(GET).build();
		RequestMappingInfo oneMethodOneParam = paths().methods(GET).params("foo").build();

		Comparator<RequestMappingInfo> comparator =
				(info, otherInfo) -> info.compareTo(otherInfo, new MockHttpServletRequest());

		List<RequestMappingInfo> list = asList(noMethods, oneMethod, oneMethodOneParam);
		Collections.shuffle(list);
		Collections.sort(list, comparator);

		assertEquals(oneMethodOneParam, list.get(0));
		assertEquals(oneMethod, list.get(1));
		assertEquals(noMethods, list.get(2));
	}
