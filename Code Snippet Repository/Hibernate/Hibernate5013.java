	@Override
	public Serializable disassemble(Object value, SharedSessionContractImplementor session, Object owner) throws HibernateException {
		if ( value == null ) {
			return null;
		}
		else {
			return new ObjectTypeCacheEntry(
					session.bestGuessEntityName( value ),
					ForeignKeys.getEntityIdentifierIfNotUnsaved(
							session.bestGuessEntityName( value ),
							value,
							session
					)
			);
		}
	}
