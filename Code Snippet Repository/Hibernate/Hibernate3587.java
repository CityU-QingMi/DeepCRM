	@Override
	public TypedValue getTypedValue(Criteria subcriteria, String propertyName, Object value) throws HibernateException {
		// Detect discriminator values...
		if ( value instanceof Class ) {
			final Class entityClass = (Class) value;
			final Queryable q = SessionFactoryHelper.findQueryableUsingImports( sessionFactory, entityClass.getName() );
			if ( q != null ) {
				final Type type = q.getDiscriminatorType();
				String stringValue = q.getDiscriminatorSQLValue();
				if ( stringValue != null
						&& stringValue.length() > 2
						&& stringValue.startsWith( "'" )
						&& stringValue.endsWith( "'" ) ) {
					// remove the single quotes
					stringValue = stringValue.substring( 1, stringValue.length() - 1 );
				}

				// Convert the string value into the proper type.
				if ( type instanceof StringRepresentableType ) {
					final StringRepresentableType nullableType = (StringRepresentableType) type;
					value = nullableType.fromStringValue( stringValue );
				}
				else {
					throw new QueryException( "Unsupported discriminator type " + type );
				}
				return new TypedValue( type, value );
			}
		}
		// Otherwise, this is an ordinary value.
		return new TypedValue( getTypeUsingProjection( subcriteria, propertyName ), value );
	}
