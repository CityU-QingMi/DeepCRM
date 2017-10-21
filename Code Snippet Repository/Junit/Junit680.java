	public static FileSelector selectFile(File file) {
		Preconditions.notNull(file, "File must not be null");
		Preconditions.condition(file.isFile(),
			() -> String.format("The supplied java.io.File [%s] must represent an existing file", file));
		try {
			return new FileSelector(file.getCanonicalPath());
		}
		catch (IOException ex) {
			throw new PreconditionViolationException("Failed to retrieve canonical path for file: " + file, ex);
		}
	}
