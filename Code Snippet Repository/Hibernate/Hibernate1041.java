	private static DynamicType.Builder<?> addFieldWithGetterAndSetter(
			DynamicType.Builder<?> builder,
			Class<?> type,
			String fieldName,
			String getterName,
			String setterName) {
		return builder.defineField( fieldName, type, Visibility.PRIVATE, FieldManifestation.TRANSIENT )
				.annotateField( AnnotationDescription.Builder.ofType( Transient.class ).build() )
				.defineMethod( getterName, type, Visibility.PUBLIC )
				.intercept( FieldAccessor.ofField( fieldName ) )
				.defineMethod( setterName, void.class, Visibility.PUBLIC )
				.withParameters( type )
				.intercept( FieldAccessor.ofField( fieldName ) );
	}
