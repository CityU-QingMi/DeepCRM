	@Test
	public void testServletContextResourcePatternResolver() throws IOException {
		final Set<String> paths = new HashSet<>();
		paths.add("/WEB-INF/context1.xml");
		paths.add("/WEB-INF/context2.xml");

		MockServletContext sc = new MockServletContext("classpath:org/springframework/web/context") {
			@Override
			public Set<String> getResourcePaths(String path) {
				if ("/WEB-INF/".equals(path)) {
					return paths;
				}
				return null;
			}
		};

		ServletContextResourcePatternResolver rpr = new ServletContextResourcePatternResolver(sc);
		Resource[] found = rpr.getResources("/WEB-INF/*.xml");
		Set<String> foundPaths = new HashSet<>();
		for (Resource resource : found) {
			foundPaths.add(((ServletContextResource) resource).getPath());
		}
		assertEquals(2, foundPaths.size());
		assertTrue(foundPaths.contains("/WEB-INF/context1.xml"));
		assertTrue(foundPaths.contains("/WEB-INF/context2.xml"));
	}
