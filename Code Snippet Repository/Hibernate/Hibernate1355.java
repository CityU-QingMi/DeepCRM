	public ToOneFkSecondPass(
			ToOne value,
			Ejb3JoinColumn[] columns,
			boolean unique,
			String entityClassName,
			String path,
			MetadataBuildingContext buildingContext) {
		super( value, columns );
		this.buildingContext = buildingContext;
		this.unique = unique;
		this.entityClassName = entityClassName;
		this.path = entityClassName != null ? path.substring( entityClassName.length() + 1 ) : path;
	}
