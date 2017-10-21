	protected String getShortPayloadLogMessage(Object payload) {
		if (payload instanceof String) {
			String payloadText = (String) payload;
			return (payloadText.length() < 80) ?
				" payload=" + payloadText :
				" payload=" + payloadText.substring(0, 80) + "...(truncated)";
		}
		else if (payload instanceof byte[]) {
			byte[] bytes = (byte[]) payload;
			if (isReadableContentType()) {
				return (bytes.length < 80) ?
						" payload=" + new String(bytes, getCharset()) :
						" payload=" + new String(Arrays.copyOf(bytes, 80), getCharset()) + "...(truncated)";
			}
			else {
				return " payload=byte[" + bytes.length + "]";
			}
		}
		else {
			String payloadText = payload.toString();
			return (payloadText.length() < 80) ?
					" payload=" + payloadText :
					" payload=" + ObjectUtils.identityToString(payload);
		}
	}
