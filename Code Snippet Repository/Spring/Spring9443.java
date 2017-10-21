		private boolean emitCachedSignals() {
			if (this.item != null) {
				requiredWriteSubscriber().onNext(this.item);
			}
			if (this.error != null) {
				requiredWriteSubscriber().onError(this.error);
				return true;
			}
			if (this.completed) {
				requiredWriteSubscriber().onComplete();
				return true;
			}
			return false;
		}
