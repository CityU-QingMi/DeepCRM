	public void prepare() throws NamingException, RemoteException {
		if (this.jndiName == null) {
			throw new IllegalArgumentException("Property 'jndiName' is required");
		}

		// Initialize and cache exported object.
		this.exportedObject = getObjectToExport();

		rebind();
	}
