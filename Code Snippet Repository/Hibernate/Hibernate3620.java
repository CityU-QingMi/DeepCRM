		private void addNamedParameter(String name) {
			Integer loc = parameterCount++;
			Object o = namedParameterBindPoints.get( name );
			if ( o == null ) {
				namedParameterBindPoints.put( name, loc );
			}
			else if ( o instanceof Integer ) {
				ArrayList list = new ArrayList( 4 );
				list.add( o );
				list.add( loc );
				namedParameterBindPoints.put( name, list );
			}
			else {
				( ( List ) o ).add( loc );
			}
		}
