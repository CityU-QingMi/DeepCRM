		@Override
		public void onWritePossible() throws IOException {
			ResponseBodyProcessor processor = bodyProcessor;
			if (processor != null) {
				processor.onWritePossible();
			}
			else {
				ResponseBodyFlushProcessor flushProcessor = bodyFlushProcessor;
				if (flushProcessor != null) {
					flushProcessor.onFlushPossible();
				}
			}
		}
