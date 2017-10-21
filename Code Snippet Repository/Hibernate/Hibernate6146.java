		@Override
		public void nullSafeSet(PreparedStatement statement, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
			if (value == null) {
				statement.setNull(index, SQLTYPE);
			} else {
				@SuppressWarnings("unchecked")
				List<String> list = (List<String>) value;
				StringBuilder sb = new StringBuilder();

				for (int i = 0; i < list.size(); i++) {
					if (i != 0) {
						sb.append('|');
					}
					sb.append(list.get(i));
				}

				statement.setString(index, sb.toString());
			}
		}
