	@Test
	public void testNaturalIdCheck() throws Exception {
        Session s = openSession();
        Transaction t = s.beginTransaction();
        Parent p = new Parent("alex");
        Child c = new Child("billy", p);
        s.persist(p);
        s.persist(c);
		t.commit();
		s.close();

        Field name = c.getClass().getDeclaredField("name");
        name.setAccessible(true);
        name.set(c, "phil");

		s = openSession();
		t = s.beginTransaction();
        try {
            s.saveOrUpdate( c );
			s.flush();
            fail( "should have failed because immutable natural ID was altered");
        }
        catch (PersistenceException e) {
			// expected
		}
		finally {
			t.rollback();
			s.close();
		}

        name.set(c, "billy");

		s = openSession();
		t = s.beginTransaction();
        s.delete(c);
        s.delete(p);
        t.commit();
        s.close();
    }
