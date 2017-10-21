	public CompositeBasedAssociationAttribute(
			AbstractCompositionAttribute source,
			SessionFactoryImplementor factory,
			int entityBasedAttributeNumber,
			String attributeName,
			AssociationType attributeType,
			BaselineAttributeInformation baselineInfo,
			int subAttributeNumber,
			AssociationKey associationKey) {
		super( source, factory, entityBasedAttributeNumber, attributeName, attributeType, baselineInfo );
		this.subAttributeNumber = subAttributeNumber;
		this.associationKey = associationKey;
	}
