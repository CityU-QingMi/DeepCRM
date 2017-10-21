	@Test
	public void testCreateAndDelete() {
		Date now = new Date();

		Session s = openSession();

		s.doWork( new ValidateSomeEntityColumns( (SessionImplementor) s ) );
		s.doWork( new ValidateRowCount( (SessionImplementor) s, SOME_ENTITY_TABLE_NAME, 0 ) );
		s.doWork( new ValidateRowCount( (SessionImplementor) s, SOME_OTHER_ENTITY_TABLE_NAME, 0 ) );

		s.beginTransaction();
		SomeEntity someEntity = new SomeEntity( now );
		SomeOtherEntity someOtherEntity = new SomeOtherEntity( 1 );
		s.save( someEntity );
		s.save( someOtherEntity );
		s.getTransaction().commit();
		s.close();

		s = openSession();

		s.doWork( new ValidateRowCount( (SessionImplementor) s, SOME_ENTITY_TABLE_NAME, 1 ) );
		s.doWork( new ValidateRowCount( (SessionImplementor) s, SOME_OTHER_ENTITY_TABLE_NAME, 1 ) );

		s.beginTransaction();
		s.delete( someEntity );
		s.delete( someOtherEntity );
		s.getTransaction().commit();

		s.doWork( new ValidateRowCount( (SessionImplementor) s, SOME_ENTITY_TABLE_NAME, 0 ) );
		s.doWork( new ValidateRowCount( (SessionImplementor) s, SOME_OTHER_ENTITY_TABLE_NAME, 0 ) );

		s.close();
	}
