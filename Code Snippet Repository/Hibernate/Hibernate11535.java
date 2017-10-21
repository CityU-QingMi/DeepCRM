	public List<Object[]> getParameters(boolean tx, boolean rw, boolean ro, boolean nonstrict) {
		ArrayList<Object[]> parameters = new ArrayList<>();
		if (tx) {
			parameters.add(TRANSACTIONAL);
		}
		if (rw) {
			parameters.add(READ_WRITE_INVALIDATION);
			parameters.add(READ_WRITE_REPLICATED);
			parameters.add(READ_WRITE_DISTRIBUTED);
		}
		if (ro) {
			parameters.add(READ_ONLY_INVALIDATION);
			parameters.add(READ_ONLY_REPLICATED);
			parameters.add(READ_ONLY_DISTRIBUTED);
		}
		if (nonstrict) {
			parameters.add(NONSTRICT_REPLICATED);
			parameters.add(NONSTRICT_DISTRIBUTED);
		}
		return parameters;
	}
