		private void log(Level level, Throwable throwable, Supplier<String> messageSupplier) {
			boolean loggable = this.julLogger.isLoggable(level);
			if (loggable || !listeners.isEmpty()) {
				LogRecord logRecord = createLogRecord(level, throwable, messageSupplier);
				if (loggable) {
					this.julLogger.log(logRecord);
				}
				listeners.forEach(l -> l.logRecordSubmitted(logRecord));
			}
		}
