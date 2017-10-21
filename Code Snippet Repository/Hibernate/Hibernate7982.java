	@Test
    public void testInsertWithSubqueriesAndNamedParams() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		org.hibernate.Query q = s.createQuery( "insert into Pickup (id, owner, vin) select :id, (select a.description from Animal a where a.description = :description), :vin from Car" );
		q.setParameter("id", 5l);
        q.setParameter("description", "Frog");
        q.setParameter("vin", "some");

		q.executeUpdate();

		t.commit();
		t = s.beginTransaction();

        try {
            org.hibernate.Query q1 = s.createQuery( "insert into Pickup (id, owner, vin) select :id, (select :description from Animal a where a.description = :description), :vin from Car" );
            fail("Unsupported exception should have been thrown");
        }
		catch (IllegalArgumentException e) {
			assertTyping( QueryException.class, e.getCause() );
		}
		catch(QueryException e) {
            assertTrue(e.getMessage().indexOf("Use of parameters in subqueries of INSERT INTO DML statements is not supported.") > -1);
        }

        t.commit();
        t = s.beginTransaction();

		s.createQuery( "delete Vehicle" ).executeUpdate();

		t.commit();
		s.close();

		data.cleanup();
	}
