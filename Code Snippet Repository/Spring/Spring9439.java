		@Override
		public final void onNext(T item) {
			if (this.readyToWrite) {
				requiredWriteSubscriber().onNext(item);
				return;
			}
			//FIXME revisit in case of reentrant sync deadlock
			synchronized (this) {
				if (this.readyToWrite) {
					requiredWriteSubscriber().onNext(item);
				}
				else if (this.beforeFirstEmission) {
					this.item = item;
					this.beforeFirstEmission = false;
					writeFunction.apply(this).subscribe(this.writeCompletionBarrier);
				}
				else {
					if (this.subscription != null) {
						this.subscription.cancel();
					}
					this.writeCompletionBarrier.onError(new IllegalStateException("Unexpected item."));
				}
			}
		}
