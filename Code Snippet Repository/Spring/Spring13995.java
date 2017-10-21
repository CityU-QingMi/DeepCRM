	public void handleSuccessiveRequest(ServerHttpRequest request, ServerHttpResponse response,
			SockJsFrameFormat frameFormat) throws SockJsException {

		synchronized (this.responseLock) {
			try {
				if (isClosed()) {
					response.getBody().write(SockJsFrame.closeFrameGoAway().getContentBytes());
					return;
				}
				this.response = response;
				this.frameFormat = frameFormat;
				ServerHttpAsyncRequestControl control = request.getAsyncRequestControl(response);
				this.asyncRequestControl = control;
				control.start(-1);
				disableShallowEtagHeaderFilter(request);
				handleRequestInternal(request, response, false);
				this.readyToSend = isActive();
			}
			catch (Throwable ex) {
				tryCloseWithSockJsTransportError(ex, CloseStatus.SERVER_ERROR);
				throw new SockJsTransportFailureException("Failed to handle SockJS receive request", getId(), ex);
			}
		}
	}
