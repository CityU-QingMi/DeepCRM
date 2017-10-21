		@Advice.OnMethodExit
		static void exit(@Advice.This Object self, @Advice.Argument(0) Collection<?> argument, @MappedBy String mappedBy) {
			if ( argument != null && Hibernate.isPropertyInitialized( argument, mappedBy ) ) {
				Object[] array = argument.toArray();
				for ( int i = 0; i < array.length; i++ ) {
					if ( Hibernate.isPropertyInitialized( array[i], mappedBy ) ) {
						Collection<Object> c = getter( array[i] );
						if ( c != self && c != null ) {
							c.add( self );
						}
					}
				}
			}
		}
