	public MappedRecord createMappedRecord(String name) throws DataAccessException {
		try {
			RecordFactory recordFactory = getRecordFactory(obtainConnectionFactory());
			return recordFactory.createMappedRecord(name);
		}
		catch (NotSupportedException ex) {
			throw new RecordTypeNotSupportedException("Creation of mapped Record not supported by connector", ex);
		}
		catch (ResourceException ex) {
			throw new CannotCreateRecordException("Creation of mapped Record failed", ex);
		}
	}
