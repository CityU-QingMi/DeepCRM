		@Nullable
		public RequestExpectation findExpectation(ClientHttpRequest request) throws IOException {
			for (RequestExpectation expectation : this.expectations) {
				try {
					expectation.match(request);
					return expectation;
				}
				catch (AssertionError error) {
					// Ignore
				}
			}
			return null;
		}
