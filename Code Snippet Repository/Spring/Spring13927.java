	private void handleCloseFrame(SockJsFrame frame) {
		CloseStatus closeStatus = CloseStatus.NO_STATUS_CODE;
		try {
			String frameData = frame.getFrameData();
			if (frameData != null) {
				String[] data = getMessageCodec().decode(frameData);
				if (data != null && data.length == 2) {
					closeStatus = new CloseStatus(Integer.valueOf(data[0]), data[1]);
				}
				if (logger.isDebugEnabled()) {
					logger.debug("Processing SockJS close frame with " + closeStatus + " in " + this);
				}
			}
		}
		catch (IOException ex) {
			if (logger.isErrorEnabled()) {
				logger.error("Failed to decode data for " + frame + " in " + this, ex);
			}
		}
		closeInternal(closeStatus);
	}
