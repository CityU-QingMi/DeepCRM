	protected void writeFrame(SockJsFrame frame) throws SockJsTransportFailureException {
		if (logger.isTraceEnabled()) {
			logger.trace("Preparing to write " + frame);
		}
		try {
			writeFrameInternal(frame);
		}
		catch (Throwable ex) {
			logWriteFrameFailure(ex);
			try {
				// Force disconnect (so we won't try to send close frame)
				disconnect(CloseStatus.SERVER_ERROR);
			}
			catch (Throwable disconnectFailure) {
				// Ignore
			}
			try {
				close(CloseStatus.SERVER_ERROR);
			}
			catch (Throwable closeFailure) {
				// Nothing of consequence, already forced disconnect
			}
			throw new SockJsTransportFailureException("Failed to write " + frame, getId(), ex);
		}
	}
