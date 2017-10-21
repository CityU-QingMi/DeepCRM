	@Override
	@SuppressWarnings("")
	public String render(Type argumentType, List args, SessionFactoryImplementor factory) throws QueryException {
		final int lastIndex = args.size()-1;
		final Object last = args.remove( lastIndex );
		if ( lastIndex==0 ) {
			return last.toString();
		}
		final Object secondLast = args.get( lastIndex-1 );
		final String nvl = "nvl(" + secondLast + ", " + last + ")";
		args.set( lastIndex-1, nvl );
		return render( argumentType, args, factory );
	}
