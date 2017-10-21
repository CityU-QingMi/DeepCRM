	@Override
	protected Statement methodBlock(FrameworkMethod method) {
		Statement statement = super.methodBlock( method );
		processAnnotations( method );
		if ( !annotationProcessorNeedsToRun() ) {
			return statement;
		}

		return new CompilationStatement(
				statement,
				testEntities,
				preCompileEntities,
				mappingFiles,
				processorOptions,
				ignoreCompilationErrors
		);
	}
