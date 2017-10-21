	@Nullable
	protected <T> T doExecute(
			final InteractionSpec spec, final Record inputRecord, @Nullable final Record outputRecord,
			@Nullable final RecordExtractor<T> outputExtractor) throws DataAccessException {

		return execute((InteractionCallback<T>) (interaction, connectionFactory) -> {
			Record outputRecordToUse = outputRecord;
			try {
				if (outputRecord != null || getOutputRecordCreator() != null) {
					// Use the CCI execute method with output record as parameter.
					if (outputRecord == null) {
						RecordFactory recordFactory = getRecordFactory(connectionFactory);
						outputRecordToUse = getOutputRecordCreator().createRecord(recordFactory);
					}
					interaction.execute(spec, inputRecord, outputRecordToUse);
				}
				else {
					outputRecordToUse = interaction.execute(spec, inputRecord);
				}
				return (outputExtractor != null ? outputExtractor.extractData(outputRecordToUse) : null);
			}
			finally {
				if (outputRecordToUse instanceof ResultSet) {
					closeResultSet((ResultSet) outputRecordToUse);
				}
			}
		});
	}
