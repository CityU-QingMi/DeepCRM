	private FileSource(File file, FilePosition filePosition) {
		Preconditions.notNull(file, "file must not be null");
		try {
			this.file = file.getCanonicalFile();
		}
		catch (IOException ex) {
			throw new JUnitException("Failed to retrieve canonical path for file: " + file, ex);
		}
		this.filePosition = filePosition;
	}
