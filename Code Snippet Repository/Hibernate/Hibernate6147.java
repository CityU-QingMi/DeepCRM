		@Override
		public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
			String string = rs.getString(names[0]);

			if (rs.wasNull()) {
				return null;
			}

			List<String> list = new ArrayList<String>();
			int lastIndex = 0, index;

			while ((index = string.indexOf('|', lastIndex)) != -1) {
				list.add(string.substring(lastIndex, index));
				lastIndex = index + 1;
			}

			if (lastIndex != string.length()) {
				list.add(string.substring(lastIndex));
			}

			return list;
		}
