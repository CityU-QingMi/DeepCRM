	private void compile(List<File> sourceFiles) throws Exception {
		List<String> options = createJavaOptions();

		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager( diagnostics, null, null );
		Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(
				sourceFiles
		);

		compileSources( options, compiler, diagnostics, fileManager, compilationUnits );
		compilationDiagnostics.addAll( diagnostics.getDiagnostics() );
		fileManager.close();
	}
