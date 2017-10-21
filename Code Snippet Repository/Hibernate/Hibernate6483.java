		@Override
		public void nullSafeSet(final PreparedStatement st, final Object value, final int index, final SharedSessionContractImplementor session) throws HibernateException, SQLException {
			if (value == null) {
				IntegerType.INSTANCE.set(st, null, index, session);
				IntegerType.INSTANCE.set(st, null, index + 1, session);
			} else {
				final YearMonth YearMonth = (YearMonth) value;

				IntegerType.INSTANCE.set(st, YearMonth.getYear(), index, session);
				IntegerType.INSTANCE.set(st, YearMonth.getMonthValue(), index + 1, session);
			}
		}
