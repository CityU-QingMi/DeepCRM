	private void compileSources(List<String> options,
			JavaCompiler compiler,
			DiagnosticCollector<JavaFileObject> diagnostics,
			StandardJavaFileManager fileManager,
			Iterable<? extends JavaFileObject> compilationUnits) {
		JavaCompiler.CompilationTask task = compiler.getTask(
				null, fileManager, diagnostics, options, null, compilationUnits
		);
		task.call();
		for ( Diagnostic<?> diagnostic : diagnostics.getDiagnostics() ) {
			log.debug( diagnostic.getMessage( null ) );
		}
	}
