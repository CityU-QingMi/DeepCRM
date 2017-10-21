	private Object readResolved(Type resolvedType, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {

		Reader reader = getReader(inputMessage);
		try {
			return readInternal(resolvedType, reader);
		}
		catch (Exception ex) {
			throw new HttpMessageNotReadableException("Could not read JSON: " + ex.getMessage(), ex);
		}
	}
