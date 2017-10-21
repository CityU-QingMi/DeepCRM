		private void handleInternal(boolean result) {
			synchronized (this) {
				if (this.result != null) {
					return;
				}
				this.result = result;
				invoke(result ? this.receiptCallbacks : this.receiptLostCallbacks);
				DefaultStompSession.this.receiptHandlers.remove(this.receiptId);
				if (this.future != null) {
					this.future.cancel(true);
				}
			}
		}
