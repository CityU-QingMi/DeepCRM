	@Test
	public void rootPatternRetrievalInJarFiles() throws IOException {
		Resource[] resources = resolver.getResources("classpath*:*.dtd");
		boolean found = false;
		for (Resource resource : resources) {
			if (resource.getFilename().equals("aspectj_1_5_0.dtd")) {
				found = true;
			}
		}
		assertTrue("Could not find aspectj_1_5_0.dtd in the root of the aspectjweaver jar", found);
	}
