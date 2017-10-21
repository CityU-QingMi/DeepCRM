	public IndexedRecord createIndexedRecord(String name) throws DataAccessException {
		try {
			RecordFactory recordFactory = getRecordFactory(obtainConnectionFactory());
			return recordFactory.createIndexedRecord(name);
		}
		catch (NotSupportedException ex) {
			throw new RecordTypeNotSupportedException("Creation of indexed Record not supported by connector", ex);
		}
		catch (ResourceException ex) {
			throw new CannotCreateRecordException("Creation of indexed Record failed", ex);
		}
	}
