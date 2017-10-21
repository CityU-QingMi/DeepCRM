		private void log(java.util.logging.Level level, Object message, Throwable exception) {
			if (logger.isLoggable(level)) {
				LogRecord rec;
				if (message instanceof LogRecord) {
					rec = (LogRecord) message;
				}
				else {
					rec = new LocationResolvingLogRecord(level, String.valueOf(message));
					rec.setLoggerName(this.name);
					rec.setResourceBundleName(logger.getResourceBundleName());
					rec.setResourceBundle(logger.getResourceBundle());
					rec.setThrown(exception);
				}
				logger.log(rec);
			}
		}
