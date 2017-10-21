	private static Set<String> getMetaAnnotationTypes(AnnotatedElement element, @Nullable Annotation composed) {
		if (composed == null) {
			return Collections.emptySet();
		}

		try {
			final Set<String> types = new LinkedHashSet<>();
			searchWithGetSemantics(composed.annotationType(), null, null, null, new SimpleAnnotationProcessor<Object>(true) {
					@Override
					@Nullable
					public Object process(@Nullable AnnotatedElement annotatedElement, Annotation annotation, int metaDepth) {
						types.add(annotation.annotationType().getName());
						return CONTINUE;
					}
				}, new HashSet<>(), 1);
			return types;
		}
		catch (Throwable ex) {
			AnnotationUtils.rethrowAnnotationConfigurationException(ex);
			throw new IllegalStateException("Failed to introspect annotations on " + element, ex);
		}
	}
