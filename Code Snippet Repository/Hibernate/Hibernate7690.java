	@Test
	public void testSerializability() {
		{
			// test ordinal mapping
			EnumType enumType = new EnumType();
			Properties properties = new Properties();
			properties.put( EnumType.ENUM, UnspecifiedEnumTypeEntity.E1.class.getName() );
			enumType.setParameterValues( properties );
			assertTrue( enumType.isOrdinal() );
			SerializationHelper.clone( enumType );
		}

		{
			// test named mapping
			EnumType enumType = new EnumType();
			Properties properties = new Properties();
			properties.put( EnumType.ENUM, UnspecifiedEnumTypeEntity.E1.class.getName() );
			properties.put( EnumType.NAMED, "true" );
			enumType.setParameterValues( properties );
			assertFalse( enumType.isOrdinal() );
			SerializationHelper.clone( enumType );
		}
	}
