	@Override
	protected void writeToResult(Object o, HttpHeaders headers, Result result) throws IOException {
		Assert.notNull(this.marshaller, "Property 'marshaller' is required");
		try {
			this.marshaller.marshal(o, result);
		}
		catch (MarshallingFailureException ex) {
			throw new HttpMessageNotWritableException("Could not write [" + o + "]", ex);
		}
	}
