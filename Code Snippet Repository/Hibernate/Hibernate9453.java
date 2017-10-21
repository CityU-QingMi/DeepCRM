		public Object transformTuple(Object[] tuple, String[] aliases) {
			Map result = new HashMap( tuple.length );
			for ( int i = 0; i < tuple.length; i++ ) {
				String alias = aliases[i];
				if ( alias != null ) {
					result.put( alias.toUpperCase(Locale.ROOT), tuple[i] );
				}
			}
			return result;
		}
