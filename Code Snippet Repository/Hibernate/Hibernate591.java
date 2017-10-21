	@Override
	public InputStream accessInputStream() {
		try {
			return new BufferedInputStream( new FileInputStream( file ) );
		}
		catch (FileNotFoundException e) {
			// should never ever ever happen, but...
			throw new ArchiveException(
					"File believed to exist based on File.exists threw error when passed to FileInputStream ctor",
					e
			);
		}
	}
