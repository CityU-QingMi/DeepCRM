	@Test
	public void testJoinFetchOfAnAnyTypeAttribute() {
		// Query translator should dis-allow join fetching of an <any/> mapping.  Let's make sure it does...
		Session session = openSession();
		try {
			session.beginTransaction();
			session.createQuery( "select p from Person p join fetch p.data" ).list();
			session.getTransaction().commit();
		}
		catch (IllegalArgumentException e) {
			//expected
			assertTyping( QuerySyntaxException.class, e.getCause() );
			session.getTransaction().rollback();
		}
		catch (QuerySyntaxException qe) {
			//expected
		}
		finally {
			session.close();
		}
	}
