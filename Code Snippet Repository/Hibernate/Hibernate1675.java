	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) {
		final String[] columns = criteriaQuery.getColumnsUsingProjection( criteria, propertyName );
		final Type type = criteriaQuery.getTypeUsingProjection( criteria, propertyName );
		final SessionFactoryImplementor factory = criteriaQuery.getFactory();
		final int[] sqlTypes = type.sqlTypes( factory );

		final StringBuilder fragment = new StringBuilder();
		for ( int i=0; i<columns.length; i++ ) {
			final StringBuilder expression = new StringBuilder();
			boolean lower = false;
			if ( ignoreCase ) {
				final int sqlType = sqlTypes[i];
				lower = sqlType == Types.VARCHAR
						|| sqlType == Types.CHAR
						|| sqlType == Types.LONGVARCHAR;
			}
			
			if ( lower ) {
				expression.append( factory.getDialect().getLowercaseFunction() )
						.append( '(' );
			}
			expression.append( columns[i] );
			if ( lower ) {
				expression.append( ')' );
			}

			fragment.append(
					factory.getDialect().renderOrderByElement(
							expression.toString(),
							null,
							ascending ? "asc" : "desc",
							nullPrecedence != null ? nullPrecedence : factory.getSettings().getDefaultNullPrecedence()
					)
			);
			if ( i < columns.length-1 ) {
				fragment.append( ", " );
			}
		}

		return fragment.toString();
	}
