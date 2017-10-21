	@Test
	public void test() {
		MockHttpServletRequestBuilder parent = new MockHttpServletRequestBuilder(HttpMethod.GET, "/");
		parent.characterEncoding("UTF-8");
		Object result = new MockMultipartHttpServletRequestBuilder("/fileUpload").merge(parent);

		assertNotNull(result);
		assertEquals(MockMultipartHttpServletRequestBuilder.class, result.getClass());

		MockMultipartHttpServletRequestBuilder builder = (MockMultipartHttpServletRequestBuilder) result;
		MockHttpServletRequest request = builder.buildRequest(new MockServletContext());
		assertEquals("UTF-8", request.getCharacterEncoding());
	}
