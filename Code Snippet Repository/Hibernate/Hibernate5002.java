	public VersionProperty(
			EntityPersister source,
			SessionFactoryImplementor sessionFactory,
			int attributeNumber,
			String attributeName,
			Type attributeType,
			BaselineAttributeInformation attributeInformation,
			VersionValue unsavedValue) {
		super( source, sessionFactory, attributeNumber, attributeName, attributeType, attributeInformation );
		this.unsavedValue = unsavedValue;
	}
