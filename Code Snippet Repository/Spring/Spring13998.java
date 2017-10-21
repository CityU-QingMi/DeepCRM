	@Override
	protected void writeFrameInternal(SockJsFrame frame) throws IOException {
		if (isActive()) {
			SockJsFrameFormat frameFormat = this.frameFormat;
			ServerHttpResponse response = this.response;
			if (frameFormat != null && response != null) {
				String formattedFrame = frameFormat.format(frame);
				if (logger.isTraceEnabled()) {
					logger.trace("Writing to HTTP response: " + formattedFrame);
				}
				response.getBody().write(formattedFrame.getBytes(SockJsFrame.CHARSET));
				response.flush();
			}
		}
	}
