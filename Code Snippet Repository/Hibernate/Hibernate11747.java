	public static <T> T withResourceLocalTx(Session session, ThrowingFunction<Session, T, Exception> consumer) throws Exception {
		Transaction transaction = session.beginTransaction();
		boolean rollingBack = false;
		try {
			T t = consumer.apply(session);
			if (transaction.getStatus() == TransactionStatus.ACTIVE) {
				transaction.commit();
			} else {
				rollingBack = true;
				transaction.rollback();
			}
			return t;
		} catch (Exception e) {
			if (!rollingBack) {
				try {
					transaction.rollback();
				} catch (Exception suppressed) {
					e.addSuppressed(suppressed);
				}
			}
			throw e;
		}
	}
