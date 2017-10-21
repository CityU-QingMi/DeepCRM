	@Override
	public InputStream getAsciiStream() throws SQLException {
		try {
			if (this.content != null) {
				return new ByteArrayInputStream(this.content.getBytes(StandardCharsets.US_ASCII));
			}
			else if (this.characterStream != null) {
				String tempContent = FileCopyUtils.copyToString(this.characterStream);
				return new ByteArrayInputStream(tempContent.getBytes(StandardCharsets.US_ASCII));
			}
			else {
				return (this.asciiStream != null ? this.asciiStream : StreamUtils.emptyInput());
			}
		}
		catch (IOException ex) {
			throw new SQLException("Failed to read stream content: " + ex);
		}
	}
