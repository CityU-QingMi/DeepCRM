	private String[] fullDiscriminatorValues() {
		if ( fullDiscriminatorValues == null ) {
			// first access; build it
			final List<String> values = new ArrayList<String>();
			for ( String subclass : getSubclassClosure() ) {
				final Queryable queryable = (Queryable) getFactory().getEntityPersister( subclass );
				if ( !queryable.isAbstract() ) {
					values.add( queryable.getDiscriminatorSQLValue() );
				}
			}
			fullDiscriminatorValues = values.toArray( new String[values.size()] );
		}

		return fullDiscriminatorValues;
	}
