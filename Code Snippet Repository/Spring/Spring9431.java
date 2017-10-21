			@Override
			public <T> void onNext(AbstractListenerWriteProcessor<T> processor, T data) {
				if (processor.isDataEmpty(data)) {
					Assert.state(processor.subscription != null, "No subscription");
					processor.subscription.request(1);
				}
				else {
					processor.receiveData(data);
					if (processor.changeState(this, RECEIVED)) {
						processor.writeIfPossible();
					}
				}
			}
