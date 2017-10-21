	public static OuterJoinableAssociation createRoot(
			AssociationType joinableType,
			String alias,
			SessionFactoryImplementor factory) {
		return new OuterJoinableAssociation(
				new PropertyPath(),
				joinableType,
				null,
				null,
				alias,
				JoinType.LEFT_OUTER_JOIN,
				null,
				false,
				factory,
				Collections.EMPTY_MAP
		);
	}
