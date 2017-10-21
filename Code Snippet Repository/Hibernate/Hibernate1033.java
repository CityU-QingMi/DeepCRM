		@Advice.OnMethodEnter
		static void enter(@FieldValue Collection<?> field, @Advice.Argument(0) Collection<?> argument, @MappedBy String mappedBy) {
			if ( field != null && Hibernate.isPropertyInitialized( field, mappedBy ) ) {
				Object[] array = field.toArray();
				for ( int i = 0; i < array.length; i++ ) {
					if ( argument == null || !argument.contains( array[i] ) ) {
						setterNull( array[i], null );
					}
				}
			}
		}
