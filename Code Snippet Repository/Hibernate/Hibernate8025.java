	@Test
    public void testSelectWithNamedParamProjection() {
        Session s = openSession();
        try {
            s.createQuery("select :someParameter, id from Car");
            fail("Should throw an unsupported exception");
        }
		catch (IllegalArgumentException e) {
			assertTyping( QueryException.class, e.getCause() );
		}
		catch(QueryException q) {
            // allright
        }
		finally {
            s.close();
        }
    }
