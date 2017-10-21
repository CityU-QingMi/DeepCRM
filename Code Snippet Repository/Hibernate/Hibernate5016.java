	private Serializable getIdentifier(Object value, SharedSessionContractImplementor session) throws HibernateException {
		try {
			return ForeignKeys.getEntityIdentifierIfNotUnsaved(
					session.bestGuessEntityName( value ),
					value,
					session
			);
		}
		catch (TransientObjectException toe) {
			return null;
		}
	}
