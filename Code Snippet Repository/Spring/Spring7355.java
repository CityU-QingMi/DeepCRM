	protected String getDetailedPayloadLogMessage(@Nullable Object payload) {
		if (payload instanceof String) {
			return " payload=" + payload;
		}
		else if (payload instanceof byte[]) {
			byte[] bytes = (byte[]) payload;
			if (isReadableContentType()) {
				return " payload=" + new String(bytes, getCharset());
			}
			else {
				return " payload=byte[" + bytes.length + "]";
			}
		}
		else {
			return " payload=" + payload;
		}
	}
