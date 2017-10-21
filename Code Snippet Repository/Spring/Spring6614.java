		public Map<String, Object> executeTest(final int[] inValue) {
			Map<String, AbstractSqlTypeValue> in = new HashMap<>();
			in.put("in", new AbstractSqlTypeValue() {
				@Override
				public Object createTypeValue(Connection con, int type, String typeName) {
					// assertEquals(Connection.class, con.getClass());
					// assertEquals(Types.ARRAY, type);
					// assertEquals("NUMBER", typeName);
					return inValue;
				}
			});
			return execute(in);
		}
