	public void handleInitialRequest(ServerHttpRequest request, ServerHttpResponse response,
			SockJsFrameFormat frameFormat) throws SockJsException {

		this.uri = request.getURI();
		this.handshakeHeaders = request.getHeaders();
		this.principal = request.getPrincipal();
		try {
			this.localAddress = request.getLocalAddress();
		}
		catch (Exception ex) {
			// Ignore
		}
		try {
			this.remoteAddress = request.getRemoteAddress();
		}
		catch (Exception ex) {
			// Ignore
		}

		synchronized (this.responseLock) {
			try {
				this.response = response;
				this.frameFormat = frameFormat;
				ServerHttpAsyncRequestControl control = request.getAsyncRequestControl(response);
				this.asyncRequestControl = control;
				control.start(-1);
				disableShallowEtagHeaderFilter(request);
				// Let "our" handler know before sending the open frame to the remote handler
				delegateConnectionEstablished();
				handleRequestInternal(request, response, true);
				// Request might have been reset (e.g. polling sessions do after writing)
				this.readyToSend = isActive();
			}
			catch (Throwable ex) {
				tryCloseWithSockJsTransportError(ex, CloseStatus.SERVER_ERROR);
				throw new SockJsTransportFailureException("Failed to open session", getId(), ex);
			}
		}
	}
