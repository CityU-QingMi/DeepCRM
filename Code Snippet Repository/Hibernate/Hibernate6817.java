	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((companyCode == null) ? 0 : companyCode.hashCode());
		result = prime * result
				+ ((contractNumber == null) ? 0 : contractNumber.hashCode());
		result = prime
				* result
				+ ((contractSequenceNumber == null) ? 0
						: contractSequenceNumber.hashCode());
		return result;
	}
