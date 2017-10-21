	public static DirectorySelector selectDirectory(File directory) {
		Preconditions.notNull(directory, "Directory must not be null");
		Preconditions.condition(directory.isDirectory(),
			() -> String.format("The supplied java.io.File [%s] must represent an existing directory", directory));
		try {
			return new DirectorySelector(directory.getCanonicalPath());
		}
		catch (IOException ex) {
			throw new PreconditionViolationException("Failed to retrieve canonical path for directory: " + directory,
				ex);
		}
	}
