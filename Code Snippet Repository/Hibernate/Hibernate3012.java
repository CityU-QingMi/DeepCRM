	@Override
	public ScrollableResults scroll(ScrollMode scrollMode) {
		before();
		try {
			return session.scroll(this, scrollMode);
		}
		finally {
			after();
		}
	}
