	public AuditAssociationQueryImpl(
			final EnversService enversService,
			final AuditReaderImplementor auditReader,
			final Q parent,
			final QueryBuilder queryBuilder,
			final String propertyName,
			final JoinType joinType,
			final Map<String, String> aliasToEntityNameMap,
			final String ownerAlias,
			final String userSuppliedAlias) {
		this.enversService = enversService;
		this.auditReader = auditReader;
		this.parent = parent;
		this.queryBuilder = queryBuilder;
		this.joinType = joinType;

		String ownerEntityName = aliasToEntityNameMap.get( ownerAlias );
		final RelationDescription relationDescription = CriteriaTools.getRelatedEntity(
				enversService,
				ownerEntityName,
				propertyName
		);
		if ( relationDescription == null ) {
			throw new IllegalArgumentException( "Property " + propertyName + " of entity " + ownerEntityName + " is not a valid association for queries" );
		}
		this.entityName = relationDescription.getToEntityName();
		this.ownerAssociationIdMapper = relationDescription.getIdMapper();
		this.ownerAlias = ownerAlias;
		this.alias = userSuppliedAlias == null ? queryBuilder.generateAlias() : userSuppliedAlias;
		aliasToEntityNameMap.put( this.alias, entityName );
		this.aliasToEntityNameMap = aliasToEntityNameMap;
		parameters = queryBuilder.addParameters( this.alias );
	}
