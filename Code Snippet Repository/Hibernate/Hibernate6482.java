		@Override
		public boolean equals(final Object x, final Object y) throws HibernateException {
			if (x == y) {
				return true;
			}
			if (x == null || y == null) {
				return false;
			}
			final YearMonth mtx = (YearMonth) x;
			final YearMonth mty = (YearMonth) y;
			return mtx.equals(mty);
		}
