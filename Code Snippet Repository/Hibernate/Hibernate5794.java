	@Test
	public void testEntityManagerFactorySerialization() throws Exception {
		EntityManagerFactory entityManagerFactory = entityManagerFactory();

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream( stream );
		out.writeObject( entityManagerFactory );
		out.close();
		byte[] serialized = stream.toByteArray();
		stream.close();
		ByteArrayInputStream byteIn = new ByteArrayInputStream( serialized );
		ObjectInputStream in = new ObjectInputStream( byteIn );
		EntityManagerFactory entityManagerFactory2 = (EntityManagerFactory) in.readObject();
		in.close();
		byteIn.close();

		assertTrue("deserialized EntityManagerFactory should be the same original EntityManagerFactory instance",
				entityManagerFactory2 == entityManagerFactory);
	}
