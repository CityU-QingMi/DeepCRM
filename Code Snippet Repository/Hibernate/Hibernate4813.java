	@Override
	public Iterable<SequenceInformation> extractMetadata(ExtractionContext extractionContext) throws SQLException {
		final IdentifierHelper identifierHelper = extractionContext.getJdbcEnvironment().getIdentifierHelper();
		final Statement statement = extractionContext.getJdbcConnection().createStatement();
		try {
			ResultSet resultSet = statement.executeQuery(
					"select SEQUENCE_CATALOG, SEQUENCE_SCHEMA, SEQUENCE_NAME, INCREMENT " +
							"from information_schema.sequences"
			);
			try {
				final List<SequenceInformation> sequenceInformationList = new ArrayList<SequenceInformation>();
				while ( resultSet.next() ) {
					sequenceInformationList.add(
							new SequenceInformationImpl(
									new QualifiedSequenceName(
											identifierHelper.toIdentifier(
													resultSet.getString( "SEQUENCE_CATALOG" )
											),
											identifierHelper.toIdentifier(
													resultSet.getString( "SEQUENCE_SCHEMA" )
											),
											identifierHelper.toIdentifier(
													resultSet.getString( "SEQUENCE_NAME" )
											)
									),
									resultSet.getInt( "INCREMENT" )
							)
					);
				}
				return sequenceInformationList;
			}
			finally {
				try {
					resultSet.close();
				}
				catch (SQLException ignore) {
				}
			}
		}
		finally {
			try {
				statement.close();
			}
			catch (SQLException ignore) {
			}
		}
	}
