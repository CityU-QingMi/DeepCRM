	private String[] decodeTreatAsRequests(Set<String> treatAsDeclarations) {
		final List<String> values = new ArrayList<String>();
		for ( String subclass : treatAsDeclarations ) {
			final Queryable queryable = (Queryable) getFactory().getEntityPersister( subclass );
			if ( !queryable.isAbstract() ) {
				values.add( queryable.getDiscriminatorSQLValue() );
			}
			else if ( queryable.hasSubclasses() ) {
				// if the treat is an abstract class, add the concrete implementations to values if any
				Set<String> actualSubClasses = queryable.getEntityMetamodel().getSubclassEntityNames();

				for ( String actualSubClass : actualSubClasses ) {
					if ( actualSubClass.equals( subclass ) ) {
						continue;
					}

					Queryable actualQueryable = (Queryable) getFactory().getEntityPersister( actualSubClass );
					if ( !actualQueryable.hasSubclasses() ) {
						values.add( actualQueryable.getDiscriminatorSQLValue() );
					}
				}
			}
		}
		return values.toArray( new String[values.size()] );
	}
