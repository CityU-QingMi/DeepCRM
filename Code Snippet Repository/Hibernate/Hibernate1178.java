	public StandardCacheEntryImpl(
			final Object[] state,
			final EntityPersister persister,
			final Object version,
			final SharedSessionContractImplementor session,
			final Object owner) throws HibernateException {
		// disassembled state gets put in a new array (we write to cache by value!)
		this.disassembledState = TypeHelper.disassemble(
				state,
				persister.getPropertyTypes(),
				persister.isLazyPropertiesCacheable() ? null : persister.getPropertyLaziness(),
				session,
				owner
		);
		this.disassembledStateText = TypeHelper.toLoggableString(
				state,
				persister.getPropertyTypes(),
				session.getFactory()
		);
		this.subclass = persister.getEntityName();
		this.version = version;
	}
