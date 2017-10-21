	public Parameters addSubParameters(String newConnective) {
		if ( connective.equals( newConnective ) ) {
			return this;
		}
		else {
			final Parameters newParams = new Parameters( alias, newConnective, queryParamCounter );
			subParameters.add( newParams );
			return newParams;
		}
	}
