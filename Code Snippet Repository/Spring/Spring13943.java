		@Override
		public void onFailure(Response response, Throwable failure) {
			if (connectFuture.setException(failure)) {
				return;
			}
			if (this.sockJsSession.isDisconnected()) {
				this.sockJsSession.afterTransportClosed(null);
			}
			else {
				this.sockJsSession.handleTransportError(failure);
				this.sockJsSession.afterTransportClosed(new CloseStatus(1006, failure.getMessage()));
			}
		}
