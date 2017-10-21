	@Override
	public Number getRevisionNumberForDate(Date date) {
		checkNotNull( date, "Date of revision" );
		checkSession();

		final Query<?> query = enversService.getRevisionInfoQueryCreator().getRevisionNumberForDateQuery( session, date );

		try {
			final Number res = (Number) query.uniqueResult();
			if ( res == null ) {
				throw new RevisionDoesNotExistException( date );
			}

			return res;
		}
		catch (NonUniqueResultException e) {
			throw new AuditException( e );
		}
	}
