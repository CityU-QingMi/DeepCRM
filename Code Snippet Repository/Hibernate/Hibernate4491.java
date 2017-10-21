	@Override
	public List<R> list() {
		beforeQuery();
		try {
			return doList();
		}
		catch (QueryExecutionRequestException he) {
			throw new IllegalStateException( he );
		}
		catch (TypeMismatchException e) {
			throw new IllegalArgumentException( e );
		}
		catch (HibernateException he) {
			throw getExceptionConverter().convert( he );
		}
		finally {
			afterQuery();
		}
	}
