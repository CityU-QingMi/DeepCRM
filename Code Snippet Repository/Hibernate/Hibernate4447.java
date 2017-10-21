	@Override
	protected FromImplementor<O, E> createCorrelationDelegate() {
		return new SetAttributeJoin<O,E>(
				criteriaBuilder(),
				getJavaType(),
				(PathImplementor<O>) getParentPath(),
				getAttribute(),
				getJoinType()
		);
	}
