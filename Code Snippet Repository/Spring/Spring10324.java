	@Override
	public PrintWriter getWriter() throws UnsupportedEncodingException {
		Assert.state(this.writerAccessAllowed, "Writer access not allowed");
		if (this.writer == null) {
			Writer targetWriter = (this.characterEncoding != null ?
					new OutputStreamWriter(this.content, this.characterEncoding) : new OutputStreamWriter(this.content));
			this.writer = new ResponsePrintWriter(targetWriter);
		}
		return this.writer;
	}
