		public NativeTupleImpl(Object[] tuple, String[] aliases) {
			if ( tuple == null ) {
				throw new HibernateException( "Tuple must not be null" );
			}
			if ( aliases == null ) {
				throw new HibernateException( "Aliases must not be null" );
			}
			if ( tuple.length != aliases.length ) {
				throw new HibernateException( "Got different size of tuples and aliases" );
			}
			this.tuple = tuple;
			for ( int i = 0; i < tuple.length; i++ ) {
				aliasToValue.put( aliases[i].toLowerCase(), tuple[i] );
			}
		}
