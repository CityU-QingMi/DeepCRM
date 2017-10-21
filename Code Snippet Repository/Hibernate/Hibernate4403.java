	public SubstringFunction(
			CriteriaBuilderImpl criteriaBuilder,
			Expression<String> value,
			int start,
			int length) {
		this(
				criteriaBuilder,
				value,
				new LiteralExpression<Integer>( criteriaBuilder, start ),
				new LiteralExpression<Integer>( criteriaBuilder, length )
		);
	}
