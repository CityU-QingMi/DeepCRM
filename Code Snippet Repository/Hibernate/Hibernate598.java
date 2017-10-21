	public ScanResultCollector(ScanEnvironment environment, ScanOptions options, ScanParameters parameters) {
		this.environment = environment;
		this.options = options;

		if ( environment.getExplicitlyListedClassNames() == null ) {
			throw new IllegalArgumentException( "ScanEnvironment#getExplicitlyListedClassNames should not return null" );
		}

		if ( environment.getExplicitlyListedMappingFiles() == null ) {
			throw new IllegalArgumentException( "ScanEnvironment#getExplicitlyListedMappingFiles should not return null" );
		}

		this.discoveredPackages = new HashSet<PackageDescriptor>();
		this.discoveredClasses = new HashSet<ClassDescriptor>();
		this.discoveredMappingFiles = new HashSet<MappingFileDescriptor>();
	}
