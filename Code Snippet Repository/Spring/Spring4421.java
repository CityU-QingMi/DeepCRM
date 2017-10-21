	static void handleIntrospectionFailure(@Nullable AnnotatedElement element, Throwable ex) {
		rethrowAnnotationConfigurationException(ex);

		Log loggerToUse = logger;
		if (loggerToUse == null) {
			loggerToUse = LogFactory.getLog(AnnotationUtils.class);
			logger = loggerToUse;
		}
		if (element instanceof Class && Annotation.class.isAssignableFrom((Class<?>) element)) {
			// Meta-annotation or (default) value lookup on an annotation type
			if (loggerToUse.isDebugEnabled()) {
				loggerToUse.debug("Failed to meta-introspect annotation [" + element + "]: " + ex);
			}
		}
		else {
			// Direct annotation lookup on regular Class, Method, Field
			if (loggerToUse.isInfoEnabled()) {
				loggerToUse.info("Failed to introspect annotations on [" + element + "]: " + ex);
			}
		}
	}
