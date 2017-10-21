	public CopyIdentifierComponentSecondPass(
			Component comp,
			String referencedEntityName,
			Ejb3JoinColumn[] joinColumns,
			MetadataBuildingContext buildingContext) {
		this.component = comp;
		this.referencedEntityName = referencedEntityName;
		this.buildingContext = buildingContext;
		this.joinColumns = joinColumns;
	}
