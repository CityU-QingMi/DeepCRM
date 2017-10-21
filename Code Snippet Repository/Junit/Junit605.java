	private void logMalformedClassName(Path classFile, String fullyQualifiedClassName, InternalError ex) {
		try {
			logger.warn(ex, () -> format("The java.lang.Class loaded from path [%s] has a malformed class name [%s].",
				classFile.toAbsolutePath(), fullyQualifiedClassName));
		}
		catch (Throwable t) {
			ex.addSuppressed(t);
			logGenericFileProcessingException(classFile, ex);
		}
	}
