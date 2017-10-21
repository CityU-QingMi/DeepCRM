	@Override
	protected final void writeInternal(Object o, @Nullable Type type, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {

		Writer writer = getWriter(outputMessage);
		if (this.jsonPrefix != null) {
			writer.append(this.jsonPrefix);
		}
		try {
			writeInternal(o, type, writer);
		}
		catch (Exception ex) {
			throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
		}
		writer.close();
	}
