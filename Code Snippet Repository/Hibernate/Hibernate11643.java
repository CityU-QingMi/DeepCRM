	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Account))
			return false;
		Account acct = (Account) obj;
		if (!safeEquals(id, acct.id))
			return false;
		if (!safeEquals(branch, acct.branch))
			return false;
		if (!safeEquals(balance, acct.balance))
			return false;
		if (!safeEquals(accountHolder, acct.accountHolder))
			return false;
		return true;
	}
