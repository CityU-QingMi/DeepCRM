	private void trackNamedParameterPositions(String name) {
		Integer loc = parameterCount++;
		Object o = namedParameters.get( name );
		if ( o == null ) {
			namedParameters.put( name, loc );
		}
		else if ( o instanceof Integer ) {
			ArrayList list = new ArrayList( 4 );
			list.add( o );
			list.add( loc );
			namedParameters.put( name, list );
		}
		else {
			( (ArrayList) o ).add( loc );
		}
	}
