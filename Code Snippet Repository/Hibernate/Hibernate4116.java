	protected boolean check(
			int rows,
			Serializable id,
			int tableNumber,
			Expectation expectation,
			PreparedStatement statement) throws HibernateException {
		try {
			expectation.verifyOutcome( rows, statement, -1 );
		}
		catch (StaleStateException e) {
			if ( !isNullableTable( tableNumber ) ) {
				if ( getFactory().getStatistics().isStatisticsEnabled() ) {
					getFactory().getStatisticsImplementor()
							.optimisticFailure( getEntityName() );
				}
				throw new StaleObjectStateException( getEntityName(), id );
			}
			return false;
		}
		catch (TooManyRowsAffectedException e) {
			throw new HibernateException(
					"Duplicate identifier in table for: " +
							MessageHelper.infoString( this, id, getFactory() )
			);
		}
		catch (Throwable t) {
			return false;
		}
		return true;
	}
