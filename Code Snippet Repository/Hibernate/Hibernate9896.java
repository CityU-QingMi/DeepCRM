	RelationQueryGenerator build(MiddleComponentData... componentDatas) {
		if ( idDatas.size() == 0 ) {
			return new OneEntityQueryGenerator(
					verEntCfg, auditStrategy, auditMiddleEntityName, referencingIdData,
					elementRevisionTypeInId, componentDatas
			);
		}
		else if ( idDatas.size() == 1 ) {
			if ( idDatas.get( 0 ).isAudited() ) {
				return new TwoEntityQueryGenerator(
						globalCfg, verEntCfg, auditStrategy, auditMiddleEntityName, referencingIdData,
						idDatas.get( 0 ), elementRevisionTypeInId, keyRevisionTypeInId, componentDatas
				);
			}
			else {
				return new TwoEntityOneAuditedQueryGenerator(
						verEntCfg, auditStrategy, auditMiddleEntityName, referencingIdData,
						idDatas.get( 0 ), elementRevisionTypeInId, componentDatas
				);
			}
		}
		else if ( idDatas.size() == 2 ) {
			// All entities must be audited.
			if ( !idDatas.get( 0 ).isAudited() || !idDatas.get( 1 ).isAudited() ) {
				throw new MappingException(
						"Ternary relations using @Audited(targetAuditMode = NOT_AUDITED) are not supported."
				);
			}

			return new ThreeEntityQueryGenerator(
					globalCfg, verEntCfg, auditStrategy, auditMiddleEntityName, referencingIdData,
					idDatas.get( 0 ), idDatas.get( 1 ), elementRevisionTypeInId, componentDatas
			);
		}
		else {
			throw new IllegalStateException( "Illegal number of related entities." );
		}
	}
