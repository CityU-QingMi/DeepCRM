	@Override
	protected FromImplementor<O, X> createCorrelationDelegate() {
		return new SingularAttributeJoin<O,X>(
				criteriaBuilder(),
				getJavaType(),
				getPathSource(),
				getAttribute(),
				getJoinType()
		);
	}
