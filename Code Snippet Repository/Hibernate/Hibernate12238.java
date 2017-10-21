	private void processAnnotations(FrameworkMethod method) {
		// configuration will be added to potential class level configuration
		processWithClasses( method.getAnnotation( WithClasses.class ) );
		processWithMappingFiles( method.getAnnotation( WithMappingFiles.class ) );
		processOptions(
				method.getAnnotation( WithProcessorOption.class ),
				method.getAnnotation( WithProcessorOption.List.class )
		);

		// overrides potential class level configuration
		ignoreCompilationErrors = method.getAnnotation( IgnoreCompilationErrors.class ) != null;
	}
