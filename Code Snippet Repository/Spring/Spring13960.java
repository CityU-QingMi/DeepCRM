		public void onFailure(Throwable failure) {
			IoUtils.safeClose(this.connection);
			if (this.connectFuture.setException(failure)) {
				return;
			}
			if (this.session.isDisconnected()) {
				this.session.afterTransportClosed(null);
			}
			else {
				this.session.handleTransportError(failure);
				this.session.afterTransportClosed(new CloseStatus(1006, failure.getMessage()));
			}
		}
