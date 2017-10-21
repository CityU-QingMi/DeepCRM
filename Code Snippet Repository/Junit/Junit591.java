		private LogRecord createLogRecord(Level level, Throwable throwable, Supplier<String> messageSupplier) {
			StackTraceElement[] stack = new Throwable().getStackTrace();
			String sourceClassName = null;
			String sourceMethodName = null;
			boolean found = false;
			for (StackTraceElement element : stack) {
				String className = element.getClassName();
				if (FQCN.equals(className)) {
					found = true;
				}
				else if (found) {
					sourceClassName = className;
					sourceMethodName = element.getMethodName();
					break;
				}
			}

			LogRecord logRecord = new LogRecord(level, nullSafeGet(messageSupplier));
			logRecord.setLoggerName(this.name);
			logRecord.setThrown(throwable);
			logRecord.setSourceClassName(sourceClassName);
			logRecord.setSourceMethodName(sourceMethodName);
			logRecord.setResourceBundleName(this.julLogger.getResourceBundleName());
			logRecord.setResourceBundle(this.julLogger.getResourceBundle());

			return logRecord;
		}
