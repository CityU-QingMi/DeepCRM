	private static void applyMin(Property property, ConstraintDescriptor<?> descriptor, Dialect dialect) {
		if ( Min.class.equals( descriptor.getAnnotation().annotationType() ) ) {
			@SuppressWarnings("unchecked")
			ConstraintDescriptor<Min> minConstraint = (ConstraintDescriptor<Min>) descriptor;
			long min = minConstraint.getAnnotation().value();

			Column col = (Column) property.getColumnIterator().next();
			String checkConstraint = col.getQuotedName(dialect) + ">=" + min;
			applySQLCheck( col, checkConstraint );
		}
	}
