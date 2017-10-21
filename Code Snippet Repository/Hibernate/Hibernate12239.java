	public CompilationStatement(Statement originalStatement,
			List<Class<?>> testEntities,
			List<Class<?>> proCompileEntities,
			List<String> xmlMappingFiles,
			Map<String, String> processorOptions,
			boolean ignoreCompilationErrors) {
		this.originalStatement = originalStatement;
		this.testEntities = testEntities;
		this.preCompileEntities = proCompileEntities;
		this.xmlMappingFiles = xmlMappingFiles;
		this.processorOptions = processorOptions;
		this.ignoreCompilationErrors = ignoreCompilationErrors;
		this.compilationDiagnostics = new ArrayList<Diagnostic<?>>();
	}
