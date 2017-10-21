	protected XmlMappingException convertCastorException(XMLException ex, boolean marshalling) {
		if (ex instanceof ValidationException) {
			return new ValidationFailureException("Castor validation exception", ex);
		}
		else if (ex instanceof MarshalException) {
			if (marshalling) {
				return new MarshallingFailureException("Castor marshalling exception", ex);
			}
			else {
				return new UnmarshallingFailureException("Castor unmarshalling exception", ex);
			}
		}
		else {
			// fallback
			return new UncategorizedMappingException("Unknown Castor exception", ex);
		}
	}
