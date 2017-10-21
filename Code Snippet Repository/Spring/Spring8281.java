		@Override
		public final void match(ClientHttpRequest request) throws IOException, AssertionError {
			try {
				MockClientHttpRequest mockRequest = (MockClientHttpRequest) request;
				matchInternal(mockRequest);
			}
			catch (ParseException ex) {
				throw new AssertionError("Failed to parse JSON request content", ex);
			}
		}
