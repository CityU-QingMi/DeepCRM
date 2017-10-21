	@Test
	public void testIdClassInSuperclass() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Parent p = new Parent();
		p.setChildren( new HashSet<Child>() );

		Child ch = new Child(p);
		p.getChildren().add(ch);
		p.setDefaultChild(ch);

		try {
			s.persist(p);
			s.flush();
			fail( "should have failed because of transient entities have non-nullable, circular dependency." );
		}
		catch (IllegalStateException ex) {
			// expected
			assertThat( ex.getCause(), instanceOf( TransientPropertyValueException.class ) );
		}
		tx.rollback();
		s.close();
	}
