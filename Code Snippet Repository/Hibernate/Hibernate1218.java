	private static void setupComponentTuplizer(XProperty property, Component component) {
		if ( property == null ) {
			return;
		}
		if ( property.isAnnotationPresent( Tuplizers.class ) ) {
			for ( Tuplizer tuplizer : property.getAnnotation( Tuplizers.class ).value() ) {
				EntityMode mode = EntityMode.parse( tuplizer.entityMode() );
				//todo tuplizer.entityModeType
				component.addTuplizer( mode, tuplizer.impl().getName() );
			}
		}
		if ( property.isAnnotationPresent( Tuplizer.class ) ) {
			Tuplizer tuplizer = property.getAnnotation( Tuplizer.class );
			EntityMode mode = EntityMode.parse( tuplizer.entityMode() );
			//todo tuplizer.entityModeType
			component.addTuplizer( mode, tuplizer.impl().getName() );
		}
	}
