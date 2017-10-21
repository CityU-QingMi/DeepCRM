	private List<AuditJoinTableInfo> getAuditJoinTableRows(
			String middleEntityName, String joinColumnIdName,
			String joinColumnIdProp, String inverseJoinColumnIdName,
			String inverseJoinColumnIdProp, String revProp, String revIdProp,
			String revTypeProp) throws Exception {
		StringBuilder qryBuilder = new StringBuilder( "select " );
		qryBuilder.append( "aud " );
		qryBuilder.append( ", " ).append( joinColumnIdProp ).append( " as joinColumnId" );
		qryBuilder.append( ", " ).append( inverseJoinColumnIdProp ).append( " as inverseJoinColumnId" );
		qryBuilder.append( ", " ).append( revProp ).append( " as rev" );
		qryBuilder.append( ", " ).append( revIdProp ).append( " as revId" );
		qryBuilder.append( ", " ).append( revTypeProp ).append( " as revType" );
		qryBuilder.append( " from " ).append( middleEntityName ).append( " aud " );
		qryBuilder.append( " order by joinColumnId asc, inverseJoinColumnId asc, revId asc" );

		String query = qryBuilder.toString();

		EntityManager em = createIsolatedEntityManager();
		Query qry = em.createQuery( query );

		@SuppressWarnings("unchecked")
		List<Object[]> auditJoinTableRows = qry.getResultList();
		List<AuditJoinTableInfo> result = new ArrayList<AuditJoinTableInfo>( auditJoinTableRows.size() );

		for ( Object[] auditJoinTableRow : auditJoinTableRows ) {
			Long joinColumnId = (Long) auditJoinTableRow[1];
			Long inverseJoinColumnId = (Long) auditJoinTableRow[2];
			SequenceIdRevisionEntity rev = (SequenceIdRevisionEntity) auditJoinTableRow[3];
			RevisionType revType = (RevisionType) auditJoinTableRow[5];

			AuditJoinTableInfo info = new AuditJoinTableInfo(
					middleEntityName, rev, revType, joinColumnIdName, joinColumnId,
					inverseJoinColumnIdName, inverseJoinColumnId
			);
			result.add( info );
		}

		return result;
	}
