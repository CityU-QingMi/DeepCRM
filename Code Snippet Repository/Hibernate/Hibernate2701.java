	public void initialize(Queryable persister) {
		if ( persister.isAbstract() ) {
			throw new QueryException( "cannot insert into abstract class (no table)" );
		}
		this.persister = persister;
		initializeColumns();

		if ( getWalker().getSessionFactoryHelper().hasPhysicalDiscriminatorColumn( persister ) ) {
			discriminated = true;
			columnSpec += ", " + persister.getDiscriminatorColumnName();
		}

		resetText();
	}
