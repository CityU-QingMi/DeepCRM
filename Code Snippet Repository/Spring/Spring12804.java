	@Test
	public void handleMatchMatrixVariables() {
		MockHttpServletRequest request;
		MultiValueMap<String, String> matrixVariables;
		Map<String, String> uriVariables;

		request = new MockHttpServletRequest();
		handleMatch(request, "/{cars}", "/cars;colors=red,blue,green;year=2012");

		matrixVariables = getMatrixVariables(request, "cars");
		uriVariables = getUriTemplateVariables(request);

		assertNotNull(matrixVariables);
		assertEquals(Arrays.asList("red", "blue", "green"), matrixVariables.get("colors"));
		assertEquals("2012", matrixVariables.getFirst("year"));
		assertEquals("cars", uriVariables.get("cars"));

		request = new MockHttpServletRequest();
		handleMatch(request, "/{cars:[^;]+}{params}", "/cars;colors=red,blue,green;year=2012");

		matrixVariables = getMatrixVariables(request, "params");
		uriVariables = getUriTemplateVariables(request);

		assertNotNull(matrixVariables);
		assertEquals(Arrays.asList("red", "blue", "green"), matrixVariables.get("colors"));
		assertEquals("2012", matrixVariables.getFirst("year"));
		assertEquals("cars", uriVariables.get("cars"));
		assertEquals(";colors=red,blue,green;year=2012", uriVariables.get("params"));

		request = new MockHttpServletRequest();
		handleMatch(request, "/{cars:[^;]+}{params}", "/cars");

		matrixVariables = getMatrixVariables(request, "params");
		uriVariables = getUriTemplateVariables(request);

		assertNull(matrixVariables);
		assertEquals("cars", uriVariables.get("cars"));
		assertEquals("", uriVariables.get("params"));
	}
