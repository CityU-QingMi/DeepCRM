	@Override
	public Iterator<R> iterate() {
		beforeQuery();
		try {
			return doIterate();
		}
		finally {
			afterQuery();
		}
	}
