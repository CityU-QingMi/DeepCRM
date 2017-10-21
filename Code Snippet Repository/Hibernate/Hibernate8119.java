	@Test
	public void testDateTimeArithmeticReturnTypesAndParameterGuessing() {
		QueryTranslatorImpl translator = createNewQueryTranslator( "select o.orderDate - o.orderDate from Order o" );
		assertEquals( "incorrect return type count", 1, translator.getReturnTypes().length );
		assertEquals( "incorrect return type", DoubleType.INSTANCE, translator.getReturnTypes()[0] );
		translator = createNewQueryTranslator( "select o.orderDate + 2 from Order o" );
		assertEquals( "incorrect return type count", 1, translator.getReturnTypes().length );
		assertEquals( "incorrect return type", CalendarDateType.INSTANCE, translator.getReturnTypes()[0] );
		translator = createNewQueryTranslator( "select o.orderDate -2 from Order o" );
		assertEquals( "incorrect return type count", 1, translator.getReturnTypes().length );
		assertEquals( "incorrect return type", CalendarDateType.INSTANCE, translator.getReturnTypes()[0] );

		translator = createNewQueryTranslator( "from Order o where o.orderDate > ?" );
		assertEquals( "incorrect expected param type", CalendarDateType.INSTANCE, translator.getParameterTranslations().getOrdinalParameterExpectedType( 0 ) );

		translator = createNewQueryTranslator( "select o.orderDate + ? from Order o" );
		assertEquals( "incorrect return type count", 1, translator.getReturnTypes().length );
		assertEquals( "incorrect return type", CalendarDateType.INSTANCE, translator.getReturnTypes()[0] );
		assertEquals( "incorrect expected param type", DoubleType.INSTANCE, translator.getParameterTranslations().getOrdinalParameterExpectedType( 0 ) );

	}
