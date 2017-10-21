	public ToOneIdMapper(
			IdMapper delegate,
			PropertyData propertyData,
			String referencedEntityName,
			boolean nonInsertableFake) {
		super( delegate.getServiceRegistry(), propertyData );
		this.delegate = delegate;
		this.referencedEntityName = referencedEntityName;
		this.nonInsertableFake = nonInsertableFake;
	}
