	@Test
	void directory() throws Exception {
		File canonicalDir = new File(".").getCanonicalFile();
		File relativeDir = new File("..", canonicalDir.getName());

		DirectorySource source = DirectorySource.from(relativeDir);

		assertThat(source.getUri()).isEqualTo(canonicalDir.toURI());
		assertThat(source.getFile()).isEqualTo(canonicalDir);
	}
