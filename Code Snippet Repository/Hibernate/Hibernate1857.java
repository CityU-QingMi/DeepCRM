	@Override
	public String render(Type firstArgumentType, List args, SessionFactoryImplementor factory) throws QueryException {
		if ( args.size() != 2 && args.size() != 3 ) {
			throw new QueryException( "convert() requires two or three arguments" );
		}

		final String type = (String) args.get( 1 );

		if ( args.size() == 2 ) {
			return "{fn convert(" + args.get( 0 ) + " , " + type + ")}";
		}
		else {
			return "convert(" + args.get( 0 ) + " , " + type + "," + args.get( 2 ) + ")";
		}
	}
