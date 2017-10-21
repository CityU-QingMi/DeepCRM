	@Test
	void fileWithoutPosition() throws Exception {
		File canonicalDir = new File(".").getCanonicalFile();
		File relativeDir = new File("..", canonicalDir.getName());
		File relativeFile = new File(relativeDir, "test.txt");
		File canonicalFile = relativeFile.getCanonicalFile();

		FileSource source = FileSource.from(relativeFile);

		assertThat(source.getUri()).isEqualTo(canonicalFile.toURI());
		assertThat(source.getFile()).isEqualTo(canonicalFile);
		assertThat(source.getPosition()).isEmpty();
	}
