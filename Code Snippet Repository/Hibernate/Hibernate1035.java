		@Advice.OnMethodEnter
		static void enter(@FieldValue Map<?, ?> field, @Advice.Argument(0) Map<?, ?> argument, @MappedBy String mappedBy) {
			if ( field != null && Hibernate.isPropertyInitialized( field, mappedBy ) ) {
				Object[] array = field.values().toArray();
				for ( int i = 0; i < array.length; i++ ) {
					if ( argument == null || !argument.values().contains( array[i] ) ) {
						setterNull( array[i], null );
					}
				}
			}
		}
