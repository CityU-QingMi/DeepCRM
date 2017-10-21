	public OuterJoinableAssociation(
			PropertyPath propertyPath,
			AssociationType joinableType,
			String lhsAlias,
			String[] lhsColumns,
			String rhsAlias,
			JoinType joinType,
			String withClause,
			boolean hasRestriction,
			SessionFactoryImplementor factory,
			Map enabledFilters) throws MappingException {
		this.propertyPath = propertyPath;
		this.joinableType = joinableType;
		this.lhsAlias = lhsAlias;
		this.lhsColumns = lhsColumns;
		this.rhsAlias = rhsAlias;
		this.joinType = joinType;
		this.joinable = joinableType.getAssociatedJoinable( factory );
		this.rhsColumns = JoinHelper.getRHSColumnNames( joinableType, factory );
		this.on = joinableType.getOnCondition( rhsAlias, factory, enabledFilters )
				+ ( withClause == null || withClause.trim().length() == 0 ? "" : " and ( " + withClause + " )" );
		this.hasRestriction = hasRestriction;
		this.enabledFilters = enabledFilters; // needed later for many-to-many/filter application
	}
