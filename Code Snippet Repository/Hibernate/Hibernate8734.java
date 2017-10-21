	@Override
	protected void prepareTest()
			throws Exception {

		doInHibernate( this::sessionFactory, session -> {

						   session.doWork( connection -> {
											   try(PreparedStatement statement = connection.prepareStatement(
													   "insert \n" +
															   "    into\n" +
															   "        TEST_ENTITY\n" +
															   "        (firstLobField, secondLobField, clobfield, id) \n" +
															   "    values\n" +
															   "        (?, ?, ?, -1)"
											   )) {
												   int index = 1;
												   statement.setString(index++, value1);
												   statement.setString(index++, value2);
												   statement.setString(index++, value3);

												   assertEquals( 1, statement.executeUpdate() );
											   }
										   } );
					   } );
	}
