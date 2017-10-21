	private void readHeaders(ByteBuffer byteBuffer, StompHeaderAccessor headerAccessor) {
		while (true) {
			ByteArrayOutputStream headerStream = new ByteArrayOutputStream(256);
			boolean headerComplete = false;
			while (byteBuffer.hasRemaining()) {
				if (tryConsumeEndOfLine(byteBuffer)) {
					headerComplete = true;
					break;
				}
				headerStream.write(byteBuffer.get());
			}
			if (headerStream.size() > 0 && headerComplete) {
				String header = new String(headerStream.toByteArray(), StandardCharsets.UTF_8);
				int colonIndex = header.indexOf(':');
				if (colonIndex <= 0) {
					if (byteBuffer.remaining() > 0) {
						throw new StompConversionException("Illegal header: '" + header +
								"'. A header must be of the form <name>:[<value>].");
					}
				}
				else {
					String headerName = unescape(header.substring(0, colonIndex));
					String headerValue = unescape(header.substring(colonIndex + 1));
					try {
						headerAccessor.addNativeHeader(headerName, headerValue);
					}
					catch (InvalidMimeTypeException ex) {
						if (byteBuffer.remaining() > 0) {
							throw ex;
						}
					}
				}
			}
			else {
				break;
			}
		}
	}
