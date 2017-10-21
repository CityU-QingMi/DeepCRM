	@Override
	public Class getASTNodeType(int i) {
		switch ( i ) {
			case ORDER_BY:
				return OrderByFragment.class;
			case SORT_SPEC:
				return SortSpecification.class;
			case ORDER_SPEC:
				return OrderingSpecification.class;
			case COLLATE:
				return CollationSpecification.class;
			case SORT_KEY:
				return SortKey.class;
			default:
				return NodeSupport.class;
		}
	}
