	private void createAndSetInnerQuery(CriteriaQuery criteriaQuery, SessionFactoryImplementor factory) {
		if ( innerQuery == null ) {
			//with two-deep subqueries, the same alias would get generated for
			//both using the old method (criteriaQuery.generateSQLAlias()), so
			//that is now used as a fallback if the main criteria alias isn't set
			String alias;
			if ( this.criteriaImpl.getAlias() == null ) {
				alias = criteriaQuery.generateSQLAlias();
			}
			else {
				alias = this.criteriaImpl.getAlias() + "_";
			}

			innerQuery = new CriteriaQueryTranslator(
					factory,
					criteriaImpl,
					criteriaImpl.getEntityOrClassName(),
					alias,
					criteriaQuery
				);

			params = innerQuery.getQueryParameters();
			types = innerQuery.getProjectedTypes();
		}
	}
