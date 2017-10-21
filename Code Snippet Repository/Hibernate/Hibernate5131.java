	@Override
	public Object assemble(Serializable oid, SharedSessionContractImplementor session, Object owner)
	throws HibernateException {
		//TODO: currently broken for unique-key references (does not detect
		//      change to unique key property of the associated object)
		Serializable id = (Serializable) getIdentifierType(session).assemble(oid, session, null); //the owner of the association is not the owner of the id

		if (id==null) {
			return null;
		}
		else {
			return resolveIdentifier(id, session);
		}
	}
