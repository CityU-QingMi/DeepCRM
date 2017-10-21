				@Override
				public SQLExceptionConversionDelegate initialize() {
					return new SQLExceptionConversionDelegate() {
						private final SQLStateConversionDelegate sqlStateDelegate = new SQLStateConversionDelegate(
								new ConversionContext() {
									@Override
									public ViolatedConstraintNameExtracter getViolatedConstraintNameExtracter() {
										// this should never happen...
										throw new HibernateException( "Unexpected call to org.hibernate.exception.spi.ConversionContext.getViolatedConstraintNameExtracter" );
									}
								}
						);

						@Override
						public JDBCException convert(SQLException sqlException, String message, String sql) {
							JDBCException exception = sqlStateDelegate.convert( sqlException, message, sql );
							if ( exception == null ) {
								// assume this is either a set-up problem or a problem connecting, which we will
								// categorize the same here.
								exception = new JDBCConnectionException( message, sqlException, sql );
							}
							return exception;
						}
					};
				}
