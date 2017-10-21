	public CompilationRunner(Class<?> clazz) throws InitializationError {
		super( clazz );
		this.testEntities = new ArrayList<Class<?>>();
		this.preCompileEntities = new ArrayList<Class<?>>();
		this.mappingFiles = new ArrayList<String>();
		this.processorOptions = new HashMap<String, String>();
		Package pkg = clazz.getPackage();
		this.packageName = pkg != null ? pkg.getName() : null;

		processWithClasses( clazz.getAnnotation( WithClasses.class ) );
		processWithMappingFiles( clazz.getAnnotation( WithMappingFiles.class ) );
		processOptions(
				clazz.getAnnotation( WithProcessorOption.class ),
				clazz.getAnnotation( WithProcessorOption.List.class )
		);

		ignoreCompilationErrors = clazz.getAnnotation( IgnoreCompilationErrors.class ) != null;
	}
