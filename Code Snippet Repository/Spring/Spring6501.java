	@Test
	public void testOtherConstructors() throws SQLException {
		// a bit BS, but we need to test them, as long as they don't throw exceptions

		SqlLobValue lob = new SqlLobValue("bla");
		lob.setTypeValue(preparedStatement, 1, Types.CLOB, "test");

		try {
			lob = new SqlLobValue("bla".getBytes());
			lob.setTypeValue(preparedStatement, 1, Types.CLOB, "test");
			fail("IllegalArgumentException should have been thrown");
		}
		catch (IllegalArgumentException e) {
			// expected
		}

		lob = new SqlLobValue(new ByteArrayInputStream("bla".getBytes()), 3);
		lob.setTypeValue(preparedStatement, 1, Types.CLOB, "test");

		lob = new SqlLobValue(new InputStreamReader(new ByteArrayInputStream(
				"bla".getBytes())), 3);
		lob.setTypeValue(preparedStatement, 1, Types.CLOB, "test");

		// same for BLOB
		lob = new SqlLobValue("bla");
		lob.setTypeValue(preparedStatement, 1, Types.BLOB, "test");

		lob = new SqlLobValue("bla".getBytes());
		lob.setTypeValue(preparedStatement, 1, Types.BLOB, "test");

		lob = new SqlLobValue(new ByteArrayInputStream("bla".getBytes()), 3);
		lob.setTypeValue(preparedStatement, 1, Types.BLOB, "test");

		lob = new SqlLobValue(new InputStreamReader(new ByteArrayInputStream(
				"bla".getBytes())), 3);

		try {
			lob.setTypeValue(preparedStatement, 1, Types.BLOB, "test");
			fail("IllegalArgumentException should have been thrown");
		}
		catch (IllegalArgumentException e) {
			// expected
		}
	}
