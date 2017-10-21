			@Override
			public <T> void writeComplete(AbstractListenerWriteFlushProcessor<T> processor) {
				try {
					processor.flush();
				}
				catch (IOException ex) {
					processor.flushingFailed(ex);
					return;
				}
				if (processor.subscriberCompleted) {
					if (processor.isFlushPending()) {
						// Ensure the final flush
						processor.changeState(this, FLUSHING);
						processor.flushIfPossible();
					}
					else if (processor.changeState(this, COMPLETED)) {
						processor.resultPublisher.publishComplete();
					}
				}
				else {
					if (processor.changeState(this, REQUESTED)) {
						Assert.state(processor.subscription != null, "No subscription");
						processor.subscription.request(1);
					}
				}
			}
