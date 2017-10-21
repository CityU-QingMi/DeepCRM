	private static String readScript(EncodedResource resource, @Nullable String commentPrefix,
			@Nullable String separator) throws IOException {

		LineNumberReader lnr = new LineNumberReader(resource.getReader());
		try {
			return readScript(lnr, commentPrefix, separator);
		}
		finally {
			lnr.close();
		}
	}
