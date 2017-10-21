	@SuppressWarnings("")
	@Nullable
	public T decode(M message) throws DecodeException {
		try {
			return (T) getConversionService().convert(message, getMessageType(), getType());
		}
		catch (ConversionException ex) {
			if (message instanceof String) {
				throw new DecodeException((String) message,
						"Unable to decode websocket message using ConversionService", ex);
			}
			if (message instanceof ByteBuffer) {
				throw new DecodeException((ByteBuffer) message,
						"Unable to decode websocket message using ConversionService", ex);
			}
			throw ex;
		}
	}
