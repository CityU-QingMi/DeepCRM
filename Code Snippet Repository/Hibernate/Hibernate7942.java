	@Test
	public void testCastInSelect() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Animal a = new Animal();
		a.setBodyWeight(12.4f);
		a.setDescription("an animal");
		s.persist(a);
		Object bodyWeight = s.createQuery("select cast(bodyWeight as integer) from Animal").uniqueResult();
		assertTrue( Integer.class.isInstance( bodyWeight ) );
		assertEquals( 12, bodyWeight );

		bodyWeight = s.createQuery("select cast(bodyWeight as big_decimal) from Animal").uniqueResult();
		assertTrue( BigDecimal.class.isInstance( bodyWeight ) );
		assertEquals( a.getBodyWeight(), ( (BigDecimal) bodyWeight ).floatValue(), .01 );

		Object literal = s.createQuery("select cast(10000000 as big_integer) from Animal").uniqueResult();
		assertTrue( BigInteger.class.isInstance( literal ) );
		assertEquals( BigInteger.valueOf( 10000000 ), literal );
		s.delete(a);
		t.commit();
		s.close();
	}
