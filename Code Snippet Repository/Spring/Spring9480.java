			@Override
			void subscribe(WriteResultPublisher publisher, Subscriber<? super Void> subscriber) {
				Assert.notNull(subscriber, "Subscriber must not be null");
				publisher.subscriber = subscriber;
				if (publisher.changeState(this, SUBSCRIBED)) {
					Subscription subscription = new ResponseBodyWriteResultSubscription(publisher);
					subscriber.onSubscribe(subscription);
					if (publisher.publisherCompleted) {
						publisher.publishComplete();
					}
					else {
						Throwable publisherError = publisher.publisherError;
						if (publisherError != null) {
							publisher.publishError(publisherError);
						}
					}
				}
				else {
					throw new IllegalStateException(toString());
				}
			}
