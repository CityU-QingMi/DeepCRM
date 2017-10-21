		@Advice.OnMethodEnter
		static void enter(@Advice.This Object self, @FieldValue Collection<?> field, @Advice.Argument(0) Collection<?> argument, @MappedBy String mappedBy) {
			if ( field != null && Hibernate.isPropertyInitialized( field, mappedBy ) ) {
				Object[] array = field.toArray();
				for ( int i = 0; i < array.length; i++ ) {
					if ( argument == null || !argument.contains( array[i] ) ) {
						getter( array[i] ).remove( self );
					}
				}
			}
		}
