	@Test
	public void testSaving() {
		byte[] value = buildRecursively( ARRAY_SIZE, true );

		Session s = openSession();
		s.beginTransaction();
		LongByteArrayHolder entity = new LongByteArrayHolder();
		entity.setLongByteArray( value );
		s.persist( entity );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		entity = ( LongByteArrayHolder ) s.get( LongByteArrayHolder.class, entity.getId() );
		Assert.assertEquals( ARRAY_SIZE, entity.getLongByteArray().length );
		assertEquals( value, entity.getLongByteArray() );
		s.delete( entity );
		s.getTransaction().commit();
		s.close();
	}
