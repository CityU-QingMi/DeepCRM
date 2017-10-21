	@Override
	public Transaction beginTransaction() {
		checkOpen();

		Transaction result = getTransaction();
		result.begin();

		this.timestamp = factory.getCache().getRegionFactory().nextTimestamp();

		return result;
	}
