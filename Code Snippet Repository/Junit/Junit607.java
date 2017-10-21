	static CloseablePath create(URI uri) throws IOException, URISyntaxException {
		if (JAR_URI_SCHEME.equals(uri.getScheme())) {
			String[] parts = uri.toString().split(JAR_URI_SEPARATOR);
			String jarUri = parts[0];
			String jarEntry = parts[1];
			return createForJarFileSystem(new URI(jarUri), fileSystem -> fileSystem.getPath(jarEntry));
		}
		if (uri.getScheme().equals(FILE_URI_SCHEME) && uri.getPath().endsWith(JAR_FILE_EXTENSION)) {
			return createForJarFileSystem(new URI(JAR_URI_SCHEME + ':' + uri),
				fileSystem -> fileSystem.getRootDirectories().iterator().next());
		}
		return new CloseablePath(Paths.get(uri), NULL_CLOSEABLE);
	}
