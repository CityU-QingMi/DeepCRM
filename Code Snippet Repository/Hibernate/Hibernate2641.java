		public EntityJoinJoinSequenceImpl(
				SessionFactoryImplementor factory,
				EntityType entityType,
				String entityTableText,
				String entityTableAlias,
				JoinType joinType) {
			super( factory );
			this.factory = factory;
			this.entityType = entityType;
			this.entityTableText = entityTableText;
			this.entityTableAlias = entityTableAlias;
			this.joinType = joinType;

			setUseThetaStyle( false );
		}
