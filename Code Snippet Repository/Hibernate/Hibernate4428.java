	public AbstractJoinImpl(
			CriteriaBuilderImpl criteriaBuilder,
			Class<X> javaType,
			PathSource<Z> pathSource,
			Attribute<? super Z, ?> joinAttribute,
			JoinType joinType) {
		super( criteriaBuilder, javaType, pathSource );
		this.joinAttribute = joinAttribute;
		this.joinType = joinType;
	}
