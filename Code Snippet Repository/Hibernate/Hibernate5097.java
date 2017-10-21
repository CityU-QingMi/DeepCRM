	@Override
	public void setParameterValues(Properties parameters) {
		// IMPL NOTE: we handle 2 distinct cases here:
		// 		1) we are passed a ParameterType instance in the incoming Properties - generally
		//			speaking this indicates the annotation-binding case, and the passed ParameterType
		//			represents information about the attribute and annotation
		//		2) we are not passed a ParameterType - generally this indicates a hbm.xml binding case.
		final ParameterType reader = (ParameterType) parameters.get( PARAMETER_TYPE );

		if ( reader != null ) {
			enumClass = reader.getReturnedClass().asSubclass( Enum.class );

			final boolean isOrdinal;
			final javax.persistence.EnumType enumType = getEnumType( reader );
			if ( enumType == null ) {
				isOrdinal = true;
			}
			else if ( javax.persistence.EnumType.ORDINAL.equals( enumType ) ) {
				isOrdinal = true;
			}
			else if ( javax.persistence.EnumType.STRING.equals( enumType ) ) {
				isOrdinal = false;
			}
			else {
				throw new AssertionFailure( "Unknown EnumType: " + enumType );
			}

			if ( isOrdinal ) {
				this.enumValueMapper = new OrdinalEnumValueMapper();
			}
			else {
				this.enumValueMapper = new NamedEnumValueMapper();
			}
			sqlType = enumValueMapper.getSqlType();
		}
		else {
			final String enumClassName = (String) parameters.get( ENUM );
			try {
				enumClass = ReflectHelper.classForName( enumClassName, this.getClass() ).asSubclass( Enum.class );
			}
			catch ( ClassNotFoundException exception ) {
				throw new HibernateException( "Enum class not found: " + enumClassName, exception );
			}

			this.enumValueMapper = interpretParameters( parameters );
			this.sqlType = enumValueMapper.getSqlType();
		}
	}
