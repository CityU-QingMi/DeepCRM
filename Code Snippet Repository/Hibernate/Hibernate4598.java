	private <T> T doTheWork(WorkExecutorVisitable<T> work) {
		try {
			// obtain our isolated connection
			Connection connection = jdbcConnectionAccess().obtainConnection();
			try {
				// do the actual work
				return work.accept( new WorkExecutor<T>(), connection );
			}
			catch (HibernateException e) {
				throw e;
			}
			catch (Exception e) {
				throw new HibernateException( "Unable to perform isolated work", e );
			}
			finally {
				try {
					// no matter what, release the connection (handle)
					jdbcConnectionAccess().releaseConnection( connection );
				}
				catch (Throwable ignore) {
					LOG.unableToReleaseIsolatedConnection( ignore );
				}
			}
		}
		catch (SQLException e) {
			throw sqlExceptionHelper().convert( e, "unable to obtain isolated JDBC connection" );
		}
	}
