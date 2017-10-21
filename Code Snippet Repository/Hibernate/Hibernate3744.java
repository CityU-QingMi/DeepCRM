	public JoinDefinedByMetadata createCollectionJoin(
			QuerySpace leftHandSide,
			String lhsPropertyName,
			CollectionQuerySpace rightHandSide,
			boolean rightHandSideRequired,
			CollectionType joinedPropertyType,
			SessionFactoryImplementor sessionFactory) {
		return new JoinImpl(
				leftHandSide,
				lhsPropertyName,
				rightHandSide,
				joinedPropertyType.getAssociatedJoinable( sessionFactory ).getKeyColumnNames(),
				joinedPropertyType,
				rightHandSideRequired
		);
	}
