	protected String assertInternallyConsistent(Properties params) {

		Boolean dynamic = Boolean.valueOf( params.getProperty( DynamicParameterizedType.IS_DYNAMIC ) );
		Assert.assertTrue( dynamic );

		String returnedClass = params.getProperty( DynamicParameterizedType.RETURNED_CLASS );
		Assert.assertEquals( String.class.getName(), returnedClass );

		Boolean primaryKey = Boolean.valueOf( params.getProperty( DynamicParameterizedType.IS_PRIMARY_KEY ) );
		Assert.assertFalse( primaryKey );

		String accessType = params.getProperty( DynamicParameterizedType.ACCESS_TYPE );
		Assert.assertEquals( "field", accessType );

		String entity = params.getProperty( DynamicParameterizedType.ENTITY );
		String propertyName = params.getProperty( DynamicParameterizedType.PROPERTY );
		XProperty xproperty = (XProperty) params.get( DynamicParameterizedType.XPROPERTY );
		Assert.assertEquals( propertyName, xproperty.getName() );
		Assert.assertEquals( entity, xproperty.getDeclaringClass().getName() );
		Assert.assertEquals( String.class.getName(), xproperty.getType().getName() );

		String tableName = propertyName.toUpperCase().split( "_" )[0];
		String columnName = propertyName.toUpperCase().split( "_" )[1];
		ParameterType parameterType = (ParameterType) params.get( DynamicParameterizedType.PARAMETER_TYPE );
		Assert.assertEquals( 1, parameterType.getColumns().length );
		Assert.assertEquals( columnName, parameterType.getColumns()[0] );
		Assert.assertEquals( String.class, parameterType.getReturnedClass() );
		Assert.assertEquals( tableName, parameterType.getTable() );

		String value = tableName + "." + columnName;
		if ( params.containsKey( "suffix" ) ) {
			value += "." + params.getProperty( "suffix" ).toUpperCase();
		}

		return value;
	}
