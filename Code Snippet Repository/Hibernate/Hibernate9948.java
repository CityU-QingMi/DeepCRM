	public EntityConfiguration(
			String versionsEntityName,
			String entityClassName,
			IdMappingData idMappingData,
			ExtendedPropertyMapper propertyMapper,
			String parentEntityName) {
		this.versionsEntityName = versionsEntityName;
		this.entityClassName = entityClassName;
		this.idMappingData = idMappingData;
		this.propertyMapper = propertyMapper;
		this.parentEntityName = parentEntityName;

		this.relations = new HashMap<>();
	}
