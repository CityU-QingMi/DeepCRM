	@Test
	public void testServletContextResourcePatternResolverWithUnboundedPatternPath() throws IOException {
		final Set<String> dirs = new HashSet<>();
		dirs.add("/WEB-INF/mydir1/");
		dirs.add("/WEB-INF/mydir2/");

		final Set<String> paths = new HashSet<>();
		paths.add("/WEB-INF/mydir2/context2.xml");
		paths.add("/WEB-INF/mydir2/mydir3/");

		MockServletContext sc = new MockServletContext("classpath:org/springframework/web/context") {
			@Override
			public Set<String> getResourcePaths(String path) {
				if ("/WEB-INF/".equals(path)) {
					return dirs;
				}
				if ("/WEB-INF/mydir1/".equals(path)) {
					return Collections.singleton("/WEB-INF/mydir1/context1.xml");
				}
				if ("/WEB-INF/mydir2/".equals(path)) {
					return paths;
				}
				if ("/WEB-INF/mydir2/mydir3/".equals(path)) {
					return Collections.singleton("/WEB-INF/mydir2/mydir3/context3.xml");
				}
				return null;
			}
		};

		ServletContextResourcePatternResolver rpr = new ServletContextResourcePatternResolver(sc);
		Resource[] found = rpr.getResources("/WEB-INF/**/*.xml");
		Set<String> foundPaths = new HashSet<>();
		for (Resource resource : found) {
			foundPaths.add(((ServletContextResource) resource).getPath());
		}
		assertEquals(3, foundPaths.size());
		assertTrue(foundPaths.contains("/WEB-INF/mydir1/context1.xml"));
		assertTrue(foundPaths.contains("/WEB-INF/mydir2/context2.xml"));
		assertTrue(foundPaths.contains("/WEB-INF/mydir2/mydir3/context3.xml"));
	}
