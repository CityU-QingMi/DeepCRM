	@Test
	public void testBitAnd() {
		MathEntity me = new MathEntity();
		me.setValue( 5 );
		
		Session s = openSession();
		s.beginTransaction();
		Long id = (Long) s.save( me );
		s.getTransaction().commit();
		s.close();
		
		s = openSession();
		s.beginTransaction();
		int value1 = ((Integer) s.createQuery( "select bitand(m.value,0) from MathEntity m where m.id=" + id ).uniqueResult()).intValue();
		int value2 = ((Integer) s.createQuery( "select bitand(m.value,2) from MathEntity m where m.id=" + id ).uniqueResult()).intValue();
		int value3 = ((Integer )s.createQuery( "select bitand(m.value,3) from MathEntity m where m.id=" + id ).uniqueResult()).intValue();
		s.getTransaction().commit();
		s.close();

		assertEquals(value1, 0);
		assertEquals(value2, 0);
		assertEquals(value3, 1);
	}
