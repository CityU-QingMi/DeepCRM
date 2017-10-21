	@Test
	public void test5() throws Exception {
		byte[] testContent = "Bla".getBytes();
		SqlLobValue lob = new SqlLobValue(new ByteArrayInputStream(testContent), 3, handler);
		lob.setTypeValue(preparedStatement, 1, Types.CLOB, "test");
		verify(creator).setClobAsAsciiStream(eq(preparedStatement), eq(1), inputStreamCaptor.capture(), eq(3));
		byte[] bytes = new byte[3];
		inputStreamCaptor.getValue().read(bytes );
		assertThat(bytes, equalTo(testContent));
	}
