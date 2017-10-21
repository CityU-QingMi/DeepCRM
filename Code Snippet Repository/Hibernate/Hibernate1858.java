	@Override
	protected String getRenderedName(List arguments) {
		final String lastArgument = (String) arguments.get( arguments.size() - 1 );
		if ( lastArgument != null && possibleStringUnits.contains( lastArgument.toUpperCase() ) ) {
			return getName();
		}
		else{
			return "substr";
		}
	}
