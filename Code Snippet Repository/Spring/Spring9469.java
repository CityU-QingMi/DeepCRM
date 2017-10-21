		@Override
		public void onError(Throwable ex) {
			ResponseBodyProcessor processor = bodyProcessor;
			if (processor != null) {
				processor.cancel();
				processor.onError(ex);
			}
			else {
				ResponseBodyFlushProcessor flushProcessor = bodyFlushProcessor;
				if (flushProcessor != null) {
					flushProcessor.cancel();
					flushProcessor.onError(ex);
				}
			}
		}
