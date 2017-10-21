		@Override
		public final void onError(Throwable ex) {
			if (this.readyToWrite) {
				requiredWriteSubscriber().onError(ex);
				return;
			}
			synchronized (this) {
				if (this.readyToWrite) {
					requiredWriteSubscriber().onError(ex);
				}
				else if (this.beforeFirstEmission) {
					this.beforeFirstEmission = false;
					this.writeCompletionBarrier.onError(ex);
				}
				else {
					this.error = ex;
				}
			}
		}
