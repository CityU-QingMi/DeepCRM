	private EntityManager spoofSerialization(EntityManager entityManager) throws IOException {
		try {
			// Serialize the incoming out to memory
			ByteArrayOutputStream serBaOut = new ByteArrayOutputStream();
			ObjectOutputStream serOut = new ObjectOutputStream( serBaOut );

			serOut.writeObject( entityManager );

			// Now, re-constitute the model from memory
			ByteArrayInputStream serBaIn =
					new ByteArrayInputStream( serBaOut.toByteArray() );
			ObjectInputStream serIn = new ObjectInputStream( serBaIn );

			EntityManager outgoing = (EntityManager) serIn.readObject();

			return outgoing;
		}
		catch (ClassNotFoundException cnfe) {
			throw new IOException( "Unable to locate class on reconstruction" );
		}
	}
