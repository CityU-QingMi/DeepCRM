	private static void createGzFile(String filePath) throws IOException {
		Resource location = new ClassPathResource("test/", GzipResourceResolverTests.class);
		Resource fileResource = new FileSystemResource(location.createRelative(filePath).getFile());
		Path gzFilePath = Paths.get(fileResource.getFile().getAbsolutePath() + ".gz");
		Files.deleteIfExists(gzFilePath);
		File gzFile = Files.createFile(gzFilePath).toFile();
		GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(gzFile));
		FileCopyUtils.copy(fileResource.getInputStream(), out);
		gzFile.deleteOnExit();
	}
