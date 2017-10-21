	private void checkBufferLimits() {
		Integer contentLength = this.expectedContentLength;
		if (contentLength != null) {
			if (contentLength > this.bufferSizeLimit) {
				throw new StompConversionException(
						"STOMP 'content-length' header value " + this.expectedContentLength +
						"  exceeds configured buffer size limit " + this.bufferSizeLimit);
			}
		}
		if (getBufferSize() > this.bufferSizeLimit) {
			throw new StompConversionException("The configured STOMP buffer size limit of " +
					this.bufferSizeLimit + " bytes has been exceeded");
		}
	}
