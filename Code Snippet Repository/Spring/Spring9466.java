		void handleError(Throwable ex) {
			ResponseBodyFlushProcessor flushProcessor = bodyFlushProcessor;
			if (flushProcessor != null) {
				flushProcessor.cancel();
				flushProcessor.onError(ex);
			}

			ResponseBodyProcessor processor = bodyProcessor;
			if (processor != null) {
				processor.cancel();
				processor.onError(ex);
			}
		}
