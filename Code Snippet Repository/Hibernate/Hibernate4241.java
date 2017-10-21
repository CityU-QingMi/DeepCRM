	@Override
	public int getUpdateCount() {
		try {
			final Output rtn = outputs().getCurrent();
			if ( rtn == null ) {
				return -1;
			}
			else if ( UpdateCountOutput.class.isInstance( rtn ) ) {
				return ( (UpdateCountOutput) rtn ).getUpdateCount();
			}
			else {
				return -1;
			}
		}
		catch (NoMoreReturnsException e) {
			return -1;
		}
		catch (HibernateException he) {
			throw getExceptionConverter().convert( he );
		}
		catch (RuntimeException e) {
			getProducer().markForRollbackOnly();
			throw e;
		}
	}
