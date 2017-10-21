	@Override
	public Serializable disassemble(Object value, SharedSessionContractImplementor session, Object owner)
			throws HibernateException {
		//remember the uk value
		
		//This solution would allow us to eliminate the owner arg to disassemble(), but
		//what if the collection was null, and then later had elements added? seems unsafe
		//session.getPersistenceContext().getCollectionEntry( (PersistentCollection) value ).getKey();
		
		final Serializable key = getKeyOfOwner(owner, session);
		if (key==null) {
			return null;
		}
		else {
			return getPersister(session)
					.getKeyType()
					.disassemble( key, session, owner );
		}
	}
