	protected Record createRecord(RecordCreator recordCreator) throws DataAccessException {
		try {
			RecordFactory recordFactory = getRecordFactory(obtainConnectionFactory());
			return recordCreator.createRecord(recordFactory);
		}
		catch (NotSupportedException ex) {
			throw new RecordTypeNotSupportedException(
					"Creation of the desired Record type not supported by connector", ex);
		}
		catch (ResourceException ex) {
			throw new CannotCreateRecordException("Creation of the desired Record failed", ex);
		}
	}
