	public CompositionBasedCompositionAttribute(
			AbstractCompositionAttribute source,
			SessionFactoryImplementor sessionFactory,
			int entityBasedAttributeNumber,
			String attributeName,
			CompositeType attributeType,
			int columnStartPosition,
			BaselineAttributeInformation baselineInfo) {
		super(
				source,
				sessionFactory,
				entityBasedAttributeNumber,
				attributeName,
				attributeType,
				columnStartPosition,
				baselineInfo
		);
	}
