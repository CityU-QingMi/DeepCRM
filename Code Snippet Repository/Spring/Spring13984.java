	protected void handleRequestInternal(ServerHttpRequest request, ServerHttpResponse response,
			AbstractHttpSockJsSession sockJsSession) throws SockJsException {

		if (sockJsSession.isNew()) {
			if (logger.isDebugEnabled()) {
				logger.debug(request.getMethod() + " " + request.getURI());
			}
			sockJsSession.handleInitialRequest(request, response, getFrameFormat(request));
		}
		else if (sockJsSession.isClosed()) {
			if (logger.isDebugEnabled()) {
				logger.debug("Connection already closed (but not removed yet) for " + sockJsSession);
			}
			SockJsFrame frame = SockJsFrame.closeFrameGoAway();
			try {
				response.getBody().write(frame.getContentBytes());
			}
			catch (IOException ex) {
				throw new SockJsException("Failed to send " + frame, sockJsSession.getId(), ex);
			}
		}
		else if (!sockJsSession.isActive()) {
			if (logger.isTraceEnabled()) {
				logger.trace("Starting " + getTransportType() + " async request.");
			}
			sockJsSession.handleSuccessiveRequest(request, response, getFrameFormat(request));
		}
		else {
			if (logger.isDebugEnabled()) {
				logger.debug("Another " + getTransportType() + " connection still open for " + sockJsSession);
			}
			String formattedFrame = getFrameFormat(request).format(SockJsFrame.closeFrameAnotherConnectionOpen());
			try {
				response.getBody().write(formattedFrame.getBytes(SockJsFrame.CHARSET));
			}
			catch (IOException ex) {
				throw new SockJsException("Failed to send " + formattedFrame, sockJsSession.getId(), ex);
			}
		}
	}
