	@Override
	protected void afterMetadataBuilt(Metadata metadata) {
		// Oracle and Postgres do not have year() functions, so we need to
		// redefine the 'User.person.yob' formula
		//
		// consider temporary until we add the capability to define
		// mapping formulas which can use dialect-registered functions...
		PersistentClass user = metadata.getEntityBinding( User.class.getName() );
		org.hibernate.mapping.Property personProperty = user.getProperty( "person" );
		Component component = ( Component ) personProperty.getValue();
		Formula f = ( Formula ) component.getProperty( "yob" ).getValue().getColumnIterator().next();

		SQLFunction yearFunction = metadata.getDatabase().getJdbcEnvironment().getDialect().getFunctions().get( "year" );
		if ( yearFunction == null ) {
			// the dialect not know to support a year() function, so rely on the
			// ANSI SQL extract function
			f.setFormula( "extract( year from dob )");
		}
		else {
			List args = new ArrayList();
			args.add( "dob" );
			f.setFormula( yearFunction.render( StandardBasicTypes.INTEGER, args, null ) );
		}
	}
