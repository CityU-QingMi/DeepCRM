	private QueryBuilder commonQueryPart(String versionsReferencedEntityName) {
		// SELECT e FROM versionsEntity e
		final QueryBuilder qb = new QueryBuilder( versionsReferencedEntityName, REFERENCED_ENTITY_ALIAS );
		qb.addProjection( null, REFERENCED_ENTITY_ALIAS, null, false );
		// WHERE
		if ( multipleIdMapperKey ) {
			// HHH-7625
			// support @OneToMany(mappedBy) to @ManyToOne @IdClass attribute.
			// e.originalId.id_ref_ed.id = :id_ref_ed
			final IdMapper mapper = getMultipleIdPrefixedMapper();
			mapper.addNamedIdEqualsToQuery( qb.getRootParameters(), null, referencingIdData.getPrefixedMapper(), true );
		}
		else {
			// e.id_ref_ed = :id_ref_ed
			referencingIdData.getPrefixedMapper().addNamedIdEqualsToQuery( qb.getRootParameters(), null, true );
		}
		return qb;
	}
