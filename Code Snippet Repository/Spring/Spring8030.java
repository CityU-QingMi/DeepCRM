	public XmlMappingException convertJibxException(JiBXException ex, boolean marshalling) {
		if (ex instanceof ValidationException) {
			return new ValidationFailureException("JiBX validation exception", ex);
		}
		else {
			if (marshalling) {
				return new MarshallingFailureException("JiBX marshalling exception", ex);
			}
			else {
				return new UnmarshallingFailureException("JiBX unmarshalling exception", ex);
			}
		}
	}
