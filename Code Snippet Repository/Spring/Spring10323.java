	@Override
	public BufferedReader getReader() throws UnsupportedEncodingException {
		if (this.content != null) {
			InputStream sourceStream = new ByteArrayInputStream(this.content);
			Reader sourceReader = (this.characterEncoding != null) ?
					new InputStreamReader(sourceStream, this.characterEncoding) :
					new InputStreamReader(sourceStream);
			return new BufferedReader(sourceReader);
		}
		else {
			return EMPTY_BUFFERED_READER;
		}
	}
