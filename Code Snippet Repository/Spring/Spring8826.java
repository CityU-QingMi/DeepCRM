	@Override
	protected final Object extractOutputData(Record record) throws DataAccessException {
		CommAreaRecord commAreaRecord = (CommAreaRecord) record;
		try {
			return bytesToObject(commAreaRecord.toByteArray());
		}
		catch (IOException ex) {
			throw new DataRetrievalFailureException("I/O exception during bytes conversion", ex);
		}
	}
