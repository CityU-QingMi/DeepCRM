	@SuppressWarnings("")
	private static Reader toReader(File file, String charsetName) {
		if ( ! file.exists() ) {
			log.warnf( "Specified schema generation script file [%s] did not exist for reading", file );
			return new Reader() {
				@Override
				public int read(char[] cbuf, int off, int len) throws IOException {
					return -1;
				}

				@Override
				public void close() throws IOException {
				}
			};
		}

		try {
			return charsetName != null ?
				new InputStreamReader( new FileInputStream(file), charsetName ) :
				new InputStreamReader( new FileInputStream(file) );
		}
		catch (IOException e) {
			throw new SchemaManagementException(
					"Unable to open specified script target file [" + file + "] for reading",
					e
			);
		}
	}
