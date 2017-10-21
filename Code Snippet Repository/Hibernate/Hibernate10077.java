	@Override
	public Date getRevisionDate(Number revision)
			throws IllegalArgumentException, RevisionDoesNotExistException, IllegalStateException {
		checkNotNull( revision, "Entity revision" );
		checkPositive( revision, "Entity revision" );
		checkSession();

		final Query<?> query = enversService.getRevisionInfoQueryCreator().getRevisionDateQuery( session, revision );

		try {
			final Object timestampObject = query.uniqueResult();
			if ( timestampObject == null ) {
				throw new RevisionDoesNotExistException( revision );
			}

			// The timestamp object is either a date or a long
			return timestampObject instanceof Date ? (Date) timestampObject : new Date( (Long) timestampObject );
		}
		catch (NonUniqueResultException e) {
			throw new AuditException( e );
		}
	}
