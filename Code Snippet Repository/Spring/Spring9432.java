			@Override
			public <T> void onWritePossible(AbstractListenerWriteProcessor<T> processor) {
				if (processor.changeState(this, WRITING)) {
					T data = processor.currentData;
					Assert.state(data != null, "No data");
					try {
						boolean writeCompleted = processor.write(data);
						if (writeCompleted) {
							processor.releaseData();
							if (!processor.subscriberCompleted) {
								processor.changeState(WRITING, REQUESTED);
								processor.suspendWriting();
								Assert.state(processor.subscription != null, "No subscription");
								processor.subscription.request(1);
							}
							else {
								processor.changeState(WRITING, COMPLETED);
								processor.writingComplete();
								processor.resultPublisher.publishComplete();
							}
						}
						else {
							processor.changeState(WRITING, RECEIVED);
							processor.writeIfPossible();
						}
					}
					catch (IOException ex) {
						processor.writingFailed(ex);
					}
				}
			}
