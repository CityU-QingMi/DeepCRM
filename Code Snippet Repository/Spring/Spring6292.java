	public void setTypes(@Nullable int[] types) throws InvalidDataAccessApiUsageException {
		if (isCompiled()) {
			throw new InvalidDataAccessApiUsageException("Cannot add parameters once query is compiled");
		}
		if (types != null) {
			for (int type : types) {
				declareParameter(new SqlParameter(type));
			}
		}
	}
