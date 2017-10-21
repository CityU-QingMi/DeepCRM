	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof AccountHolder))
			return false;
		AccountHolder pk = (AccountHolder) obj;
		if (!lastName.equals(pk.lastName))
			return false;
		if (!ssn.equals(pk.ssn))
			return false;
		return true;
	}
