	public JoinDefinedByMetadata createCompositeJoin(
			QuerySpace leftHandSide,
			String lhsPropertyName,
			CompositeQuerySpace rightHandSide,
			boolean rightHandSideRequired,
			CompositeType joinedPropertyType) {
		return new JoinImpl(
				leftHandSide,
				lhsPropertyName,
				rightHandSide,
				null,
				joinedPropertyType,
				rightHandSideRequired
		);
	}
