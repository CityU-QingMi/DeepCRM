	@Override
	public List list() {
/**/
/**/
/**/
/**/
/**/
/**/
/**/
		AuditEntitiesConfiguration verEntCfg = enversService.getAuditEntitiesConfiguration();
		String revisionPropertyPath = verEntCfg.getRevisionNumberPath();
		qb.getRootParameters().addWhereWithParam( revisionPropertyPath, "=", revision );

		// all specified conditions
		for ( AuditCriterion criterion : criterions ) {
			criterion.addToQuery(
					enversService,
					versionsReader,
					aliasToEntityNameMap,
					QueryConstants.REFERENCED_ENTITY_ALIAS,
					qb,
					qb.getRootParameters()
			);
		}

		for (final AuditAssociationQueryImpl<?> associationQuery : associationQueries) {
			associationQuery.addCriterionsToQuery( versionsReader );
		}

		Query query = buildQuery();
		List queryResult = query.list();
		return applyProjections( queryResult, revision );
	}
