	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof AccountNotAuditedOwners) ) {
			return false;
		}

		AccountNotAuditedOwners account = (AccountNotAuditedOwners) o;

		if ( accountId != null ? !accountId.equals( account.accountId ) : account.accountId != null ) {
			return false;
		}
		if ( type != null ? !type.equals( account.type ) : account.type != null ) {
			return false;
		}

		return true;
	}
