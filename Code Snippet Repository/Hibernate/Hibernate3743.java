	public JoinDefinedByMetadata createEntityJoin(
			QuerySpace leftHandSide,
			String lhsPropertyName,
			EntityQuerySpace rightHandSide,
			boolean rightHandSideRequired,
			EntityType joinedPropertyType,
			SessionFactoryImplementor sessionFactory) {
		return new JoinImpl(
				leftHandSide,
				lhsPropertyName,
				rightHandSide,
				determineRhsColumnNames( joinedPropertyType, sessionFactory ),
				joinedPropertyType,
				rightHandSideRequired
		);
	}
