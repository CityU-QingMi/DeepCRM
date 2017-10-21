	private static void matchColumnsByProperty(Property property, Map<Column, Set<Property>> columnsToProperty) {
		if ( property == null ) {
			return;
		}
		if ( "noop".equals( property.getPropertyAccessorName() )
				|| "embedded".equals( property.getPropertyAccessorName() ) ) {
			return;
		}
// FIXME cannot use subproperties becasue the caller needs top level properties
//		if ( property.isComposite() ) {
//			Iterator subProperties = ( (Component) property.getValue() ).getPropertyIterator();
//			while ( subProperties.hasNext() ) {
//				matchColumnsByProperty( (Property) subProperties.next(), columnsToProperty );
//			}
//		}
		else {
			Iterator columnIt = property.getColumnIterator();
			while ( columnIt.hasNext() ) {
				//can be a Formula so we don't cast
				Object column = columnIt.next();
				//noinspection SuspiciousMethodCalls
				if ( columnsToProperty.containsKey( column ) ) {
					columnsToProperty.get( column ).add( property );
				}
			}
		}
	}
