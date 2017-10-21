		@Override
		public void onComplete(AsyncEvent event) {
			ResponseBodyFlushProcessor flushProcessor = bodyFlushProcessor;
			if (flushProcessor != null) {
				flushProcessor.cancel();
				flushProcessor.onComplete();
			}

			ResponseBodyProcessor processor = bodyProcessor;
			if (processor != null) {
				processor.cancel();
				processor.onComplete();
			}
		}
