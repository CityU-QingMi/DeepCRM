	@Test
	public void testSerializableException() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			Query query = em.createQuery( "SELECT p FETCH JOIN p.distributors FROM Item p" );
			query.getSingleResult();
		}
		catch ( IllegalArgumentException e ) {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			ObjectOutput out = new ObjectOutputStream( stream );
			out.writeObject( e );
			out.close();
			byte[] serialized = stream.toByteArray();
			stream.close();
			ByteArrayInputStream byteIn = new ByteArrayInputStream( serialized );
			ObjectInputStream in = new ObjectInputStream( byteIn );
			IllegalArgumentException deserializedException = ( IllegalArgumentException ) in.readObject();
			in.close();
			byteIn.close();
			assertNull( deserializedException.getCause().getCause() );
			assertNull( e.getCause().getCause() );
		}
		em.getTransaction().rollback();
		em.close();


		Exception e = new HibernateException( "Exception", new NullPointerException( "NPE" ) );
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream( stream );
		out.writeObject( e );
		out.close();
		byte[] serialized = stream.toByteArray();
		stream.close();
		ByteArrayInputStream byteIn = new ByteArrayInputStream( serialized );
		ObjectInputStream in = new ObjectInputStream( byteIn );
		HibernateException deserializedException = ( HibernateException ) in.readObject();
		in.close();
		byteIn.close();
		assertNotNull( "Arbitrary exceptions nullified", deserializedException.getCause() );
		assertNotNull( e.getCause() );
	}
