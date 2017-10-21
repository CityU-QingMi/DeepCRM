	protected <T> void runBasicTests(AbstractSingleColumnStandardBasicType<T> type, T original, T copy, T different) {
		final boolean nonCopyable = Class.class.isInstance( original ) || Currency.class.isInstance( original );
		if ( ! nonCopyable ) {
			// these checks exclude classes which cannot really be cloned (singetons/enums)
			assertFalse( original == copy );
		}

		assertTrue( original == type.replace( original, copy, null, null, null ) );

		assertTrue( type.isSame( original, copy ) );
		assertTrue( type.isEqual( original, copy ) );
		assertTrue( type.isEqual( original, copy ) );
		assertTrue( type.isEqual( original, copy, null ) );

		assertFalse( type.isSame( original, different ) );
		assertFalse( type.isEqual( original, different ) );
		assertFalse( type.isEqual( original, different ) );
		assertFalse( type.isEqual( original, different, null ) );

		assertFalse( type.isDirty( original, copy , session ) );
		assertFalse( type.isDirty( original, copy , ArrayHelper.FALSE, session ) );
		assertFalse( type.isDirty( original, copy , ArrayHelper.TRUE, session ) );

		assertTrue( type.isDirty( original, different , session ) );
		assertFalse( type.isDirty( original, different , ArrayHelper.FALSE, session ) );
		assertTrue( type.isDirty( original, different , ArrayHelper.TRUE, session ) );

		assertFalse( type.isModified( original, copy, ArrayHelper.FALSE, session ) );
		assertFalse( type.isModified( original, copy, ArrayHelper.TRUE, session ) );

		assertTrue( type.isModified( original, different, ArrayHelper.FALSE, session ) );
		assertTrue( type.isModified( original, different, ArrayHelper.TRUE, session ) );
	}
