	private static void applyMax(Property property, ConstraintDescriptor<?> descriptor, Dialect dialect) {
		if ( Max.class.equals( descriptor.getAnnotation().annotationType() ) ) {
			@SuppressWarnings("unchecked")
			ConstraintDescriptor<Max> maxConstraint = (ConstraintDescriptor<Max>) descriptor;
			long max = maxConstraint.getAnnotation().value();
			Column col = (Column) property.getColumnIterator().next();
			String checkConstraint = col.getQuotedName(dialect) + "<=" + max;
			applySQLCheck( col, checkConstraint );
		}
	}
