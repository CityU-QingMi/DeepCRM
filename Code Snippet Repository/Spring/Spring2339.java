	@Nullable
	protected Method getCreateMethod(Object home) throws EjbAccessException {
		try {
			// Cache the EJB create() method that must be declared on the home interface.
			return home.getClass().getMethod("create", (Class[]) null);
		}
		catch (NoSuchMethodException ex) {
			throw new EjbAccessException("EJB home [" + home + "] has no no-arg create() method");
		}
	}
