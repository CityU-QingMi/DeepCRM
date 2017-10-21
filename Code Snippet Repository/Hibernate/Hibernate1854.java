	@Override
	public String render(Type columnType, List args, SessionFactoryImplementor factory) throws QueryException {
		if ( args.size()!=2 ) {
			throw new QueryException( "cast() requires two arguments; found : " + args.size() );
		}
		final String type = (String) args.get( 1 );
		final int[] sqlTypeCodes = factory.getTypeResolver().heuristicType( type ).sqlTypes( factory );
		if ( sqlTypeCodes.length!=1 ) {
			throw new QueryException("invalid Hibernate type for cast()");
		}
		String sqlType = factory.getDialect().getCastTypeName( sqlTypeCodes[0] );
		if ( sqlType == null ) {
			//TODO: never reached, since getExplicitHibernateTypeName() actually throws an exception!
			sqlType = type;
		}
		return "cast(" + args.get( 0 ) + " as " + sqlType + ')';
	}
