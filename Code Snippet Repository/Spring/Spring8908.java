	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		// Rely on default serialization; just initialize state after deserialization.
		ois.defaultReadObject();

		// Create template for client-side JNDI lookup.
		this.jndiTemplate = new JndiTemplate();

		// Perform a fresh lookup for JTA handles.
		initUserTransactionAndTransactionManager();
		initTransactionSynchronizationRegistry();
	}
