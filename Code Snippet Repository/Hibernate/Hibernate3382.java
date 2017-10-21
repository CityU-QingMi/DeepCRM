	public StandardJpaScanEnvironmentImpl(PersistenceUnitDescriptor persistenceUnitDescriptor) {
		this.persistenceUnitDescriptor = persistenceUnitDescriptor;

		this.explicitlyListedClassNames = persistenceUnitDescriptor.getManagedClassNames() == null
				? Collections.<String>emptyList()
				: persistenceUnitDescriptor.getManagedClassNames();
		this.explicitlyListedMappingFiles = persistenceUnitDescriptor.getMappingFileNames() == null
				? Collections.<String>emptyList()
				: persistenceUnitDescriptor.getMappingFileNames();
	}
