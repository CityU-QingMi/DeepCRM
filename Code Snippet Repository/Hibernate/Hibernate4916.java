	protected AbstractNonIdentifierAttribute(
			AttributeSource source,
			SessionFactoryImplementor sessionFactory,
			int attributeNumber,
			String attributeName,
			Type attributeType,
			BaselineAttributeInformation attributeInformation) {
		super( attributeName, attributeType );
		this.source = source;
		this.sessionFactory = sessionFactory;
		this.attributeNumber = attributeNumber;
		this.attributeInformation = attributeInformation;
	}
