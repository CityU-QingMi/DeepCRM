			public <T> void onFlushPossible(AbstractListenerWriteFlushProcessor<T> processor) {
				try {
					processor.flush();
				}
				catch (IOException ex) {
					processor.flushingFailed(ex);
					return;
				}
				if (processor.changeState(this, COMPLETED)) {
					processor.resultPublisher.publishComplete();
				}
			}
