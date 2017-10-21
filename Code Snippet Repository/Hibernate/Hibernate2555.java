	protected void postProcessDML(RestrictableStatement statement) throws SemanticException {
		statement.getFromClause().resolve();

		FromElement fromElement = (FromElement) statement.getFromClause().getFromElements().get( 0 );
		Queryable persister = fromElement.getQueryable();
		// Make #@%$^#^&# sure no alias is applied to the table name
		fromElement.setText( persister.getTableName() );

//		// append any filter fragments; the EMPTY_MAP is used under the assumption that
//		// currently enabled filters should not affect this process
//		if ( persister.getDiscriminatorType() != null ) {
//			new SyntheticAndFactory( getASTFactory() ).addDiscriminatorWhereFragment(
//			        statement,
//			        persister,
//			        java.util.Collections.EMPTY_MAP,
//			        fromElement.getTableAlias()
//			);
//		}
		if ( persister.getDiscriminatorType() != null || !queryTranslatorImpl.getEnabledFilters().isEmpty() ) {
			new SyntheticAndFactory( this ).addDiscriminatorWhereFragment(
					statement,
					persister,
					queryTranslatorImpl.getEnabledFilters(),
					fromElement.getTableAlias()
			);
		}

	}
