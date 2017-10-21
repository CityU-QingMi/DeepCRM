	protected AbstractAuditQuery(
			EnversService enversService,
			AuditReaderImplementor versionsReader,
			Class<?> cls,
			String entityName) {
		this.enversService = enversService;
		this.versionsReader = versionsReader;

		criterions = new ArrayList<>();
		entityInstantiator = new EntityInstantiator( enversService, versionsReader );

		entityClassName = cls.getName();
		this.entityName = entityName;
		versionsEntityName = enversService.getAuditEntitiesConfiguration().getAuditEntityName( entityName );
		aliasToEntityNameMap.put( REFERENCED_ENTITY_ALIAS, entityName );

		qb = new QueryBuilder( versionsEntityName, REFERENCED_ENTITY_ALIAS );
	}
