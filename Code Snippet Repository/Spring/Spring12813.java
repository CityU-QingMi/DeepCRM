	@Test
	public void getHandlerUnsatisfiedServletRequestParameterException() throws Exception {
		try {
			MockHttpServletRequest request = new MockHttpServletRequest("GET", "/params");
			this.handlerMapping.getHandler(request);
			fail("UnsatisfiedServletRequestParameterException expected");
		}
		catch (UnsatisfiedServletRequestParameterException ex) {
			List<String[]> groups = ex.getParamConditionGroups();
			assertEquals(2, groups.size());
			assertThat(Arrays.asList("foo=bar", "bar=baz"),
					containsInAnyOrder(groups.get(0)[0], groups.get(1)[0]));
		}
	}
