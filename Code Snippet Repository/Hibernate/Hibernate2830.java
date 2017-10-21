	@SuppressWarnings("")
	protected ProcessedWhereClause processWhereClause(AST whereClause) {
		if ( whereClause.getNumberOfChildren() != 0 ) {
			// If a where clause was specified in the update/delete query, use it to limit the
			// ids that will be returned and inserted into the id table...
			try {
				SqlGenerator sqlGenerator = new SqlGenerator( sessionFactory );
				sqlGenerator.whereClause( whereClause );
				String userWhereClause = sqlGenerator.getSQL().substring( 7 );  // strip the " where "
				List<ParameterSpecification> idSelectParameterSpecifications = sqlGenerator.getCollectedParameters();

				return new ProcessedWhereClause( userWhereClause, idSelectParameterSpecifications );
			}
			catch ( RecognitionException e ) {
				throw new HibernateException( "Unable to generate id select for DML operation", e );
			}
		}
		else {
			return ProcessedWhereClause.NO_WHERE_CLAUSE;
		}
	}
