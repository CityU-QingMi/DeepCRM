	@Override
	public Object assemble(Serializable cached, SharedSessionContractImplementor session, Object owner)
			throws HibernateException {
		//we must use the "remembered" uk value, since it is 
		//not available from the EntityEntry during assembly
		if (cached==null) {
			return null;
		}
		else {
			final Serializable key = (Serializable) getPersister(session)
					.getKeyType()
					.assemble( cached, session, owner);
			return resolveKey( key, session, owner );
		}
	}
