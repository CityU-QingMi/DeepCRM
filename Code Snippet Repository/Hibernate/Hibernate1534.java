	private static void applyLength(Property property, ConstraintDescriptor<?> descriptor, PropertyDescriptor propertyDescriptor) {
		if ( "org.hibernate.validator.constraints.Length".equals(
				descriptor.getAnnotation().annotationType().getName()
		)
				&& String.class.equals( propertyDescriptor.getElementClass() ) ) {
			@SuppressWarnings("unchecked")
			int max = (Integer) descriptor.getAttributes().get( "max" );
			Column col = (Column) property.getColumnIterator().next();
			if ( max < Integer.MAX_VALUE ) {
				col.setLength( max );
			}
		}
	}
