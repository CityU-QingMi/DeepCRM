	@Test
	public void testCreatingPreparedStatementCallback() throws SQLException {
		LobHandler handler = mock(LobHandler.class);
		LobCreator creator = mock(LobCreator.class);
		PreparedStatement ps = mock(PreparedStatement.class);

		given(handler.getLobCreator()).willReturn(creator);
		given(ps.executeUpdate()).willReturn(3);

		class SetValuesCalled {
			boolean b = false;
		}

		final SetValuesCalled svc = new SetValuesCalled();

		AbstractLobCreatingPreparedStatementCallback psc = new AbstractLobCreatingPreparedStatementCallback(
				handler) {
			@Override
			protected void setValues(PreparedStatement ps, LobCreator lobCreator)
					throws SQLException, DataAccessException {
				svc.b = true;
			}
		};

		assertEquals(Integer.valueOf(3), psc.doInPreparedStatement(ps));
		assertTrue(svc.b);
		verify(creator).close();
		verify(handler).getLobCreator();
		verify(ps).executeUpdate();
	}
