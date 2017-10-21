	@Override
	protected Object readFromSource(Class<?> clazz, HttpHeaders headers, Source source) throws IOException {
		Assert.notNull(this.unmarshaller, "Property 'unmarshaller' is required");
		try {
			Object result = this.unmarshaller.unmarshal(source);
			if (!clazz.isInstance(result)) {
				throw new TypeMismatchException(result, clazz);
			}
			return result;
		}
		catch (UnmarshallingFailureException ex) {
			throw new HttpMessageNotReadableException("Could not read [" + clazz + "]", ex);
		}
	}
