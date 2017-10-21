	@Override
	public boolean isNullifiable(boolean earlyInsert, SharedSessionContractImplementor session) {
		if ( getStatus() == Status.SAVING ) {
			return true;
		}
		else if ( earlyInsert ) {
			return !isExistsInDatabase();
		}
		else {
			return session.getPersistenceContext().getNullifiableEntityKeys().contains( getEntityKey() );
		}
	}
