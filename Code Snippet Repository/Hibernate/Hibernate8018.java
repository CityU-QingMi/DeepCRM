	@Test
	public void testDeleteSyntaxWithCompositeId() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		s.createQuery( "delete EntityWithCrazyCompositeKey where id.id = 1 and id.otherId = 2" ).executeUpdate();
		s.createQuery( "delete from EntityWithCrazyCompositeKey where id.id = 1 and id.otherId = 2" ).executeUpdate();
		s.createQuery( "delete from EntityWithCrazyCompositeKey e where e.id.id = 1 and e.id.otherId = 2" ).executeUpdate();

		t.commit();
		s.close();
	}
