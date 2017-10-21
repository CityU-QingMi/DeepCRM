	private Parameters(Parameters other) {
		this.alias = other.alias;
		this.connective = other.connective;
		this.queryParamCounter = other.queryParamCounter.deepCopy();

		subParameters = new ArrayList<>( other.subParameters.size() );
		for ( Parameters p : other.subParameters ) {
			subParameters.add( p.deepCopy() );
		}
		negatedParameters = new ArrayList<>( other.negatedParameters.size() );
		for ( Parameters p : other.negatedParameters ) {
			negatedParameters.add( p.deepCopy() );
		}
		expressions = new ArrayList<>( other.expressions );
		localQueryParamValues = new HashMap<>( other.localQueryParamValues );
	}
