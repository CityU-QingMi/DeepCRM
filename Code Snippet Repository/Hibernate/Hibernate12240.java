	@Override
	public void evaluate() throws Throwable {
		// some test needs to compile some classes prior to the actual classes under test
		if ( !preCompileEntities.isEmpty() ) {
			compile( getCompilationUnits( preCompileEntities ) );
		}

		// now we compile the actual test classes
		compile( getCompilationUnits( testEntities ) );

		if ( !ignoreCompilationErrors ) {
			TestUtil.assertNoCompilationError( compilationDiagnostics );
		}

		originalStatement.evaluate();
	}
