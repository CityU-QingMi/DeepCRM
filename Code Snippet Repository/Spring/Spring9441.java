		@Override
		public final void onComplete() {
			if (this.readyToWrite) {
				requiredWriteSubscriber().onComplete();
				return;
			}
			synchronized (this) {
				if (this.readyToWrite) {
					requiredWriteSubscriber().onComplete();
				}
				else if (this.beforeFirstEmission) {
					this.completed = true;
					this.beforeFirstEmission = false;
					writeFunction.apply(this).subscribe(this.writeCompletionBarrier);
				}
				else {
					this.completed = true;
				}
			}
		}
