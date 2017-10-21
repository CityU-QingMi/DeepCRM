	public BasicType determineTypeForTemporalType(TemporalType temporalType, Type baseType, Object bindValue) {
		// todo : for 6.0 make TemporalType part of org.hibernate.type.descriptor.java.JdbcRecommendedSqlTypeMappingContext
		//		then we can just ask the org.hibernate.type.basic.BasicTypeFactory to handle this based on its registry
		//
		//   - or for 6.0 make TemporalType part of the state for those BasicType impls dealing with date/time types
		//
		// 	 - or for 6.0 make TemporalType part of Binder contract
		//
		//   - or add a org.hibernate.type.TemporalType#getVariant(TemporalType)
		//
		//	 - or ...

		// todo : (5.2) review Java type handling for sanity.  This part was done quickly ;)

		final Class javaType;

		// Determine the "value java type" :
		// 		prefer to leverage the bindValue java type (if bindValue not null),
		// 		followed by the java type reported by the baseType,
		// 		fallback to java.sql.Timestamp

		if ( bindValue != null ) {
			javaType = bindValue.getClass();
		}
		else if ( baseType != null ) {
			javaType = baseType.getReturnedClass();
		}
		else {
			javaType = java.sql.Timestamp.class;
		}

		switch ( temporalType ) {
			case TIMESTAMP: {
				return resolveTimestampTemporalTypeVariant( javaType, baseType );
			}
			case DATE: {
				return resolveDateTemporalTypeVariant( javaType, baseType );
			}
			case TIME: {
				return resolveTimeTemporalTypeVariant( javaType, baseType );
			}
			default: {
				throw new IllegalArgumentException( "Unexpected TemporalType [" + temporalType + "]; expecting TIMESTAMP, DATE or TIME" );
			}
		}
	}
