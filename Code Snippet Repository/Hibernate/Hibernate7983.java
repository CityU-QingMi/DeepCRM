	@Test
    public void testSimpleInsertTypeMismatchException() {

        Session s = openSession();
        try {
            org.hibernate.Query q = s.createQuery( "insert into Pickup (id, owner, vin) select id, :owner, id from Car" );
            fail("Parameter type mismatch but no exception thrown");
        }
		catch (Throwable throwable) {
			QueryException queryException = assertTyping( QueryException.class, throwable.getCause() );
            String m = queryException.getMessage();
            // insertion type [org.hibernate.type.StringType@21e3cc77] and selection type [org.hibernate.type.LongType@7284aa02] at position 2 are not compatible [insert into Pickup (id, owner, vin) select id, :owner, id from org.hibernate.test.hql.Car]
            int st = m.indexOf("org.hibernate.type.StringType");
            int lt = m.indexOf("org.hibernate.type.LongType");
            assertTrue("type causing error not reported", st > -1);
            assertTrue("type causing error not reported", lt > -1);
            assertTrue(lt > st);
            assertTrue("wrong position of type error reported", m.indexOf("position 2") > -1);
        }
		finally {
            s.close();
        }
    }
