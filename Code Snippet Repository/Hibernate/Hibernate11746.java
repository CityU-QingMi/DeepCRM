	public static void withResourceLocalTx(Session session, ThrowingConsumer<Session, Exception> consumer) throws Exception {
		Transaction transaction = session.beginTransaction();
		boolean rollingBack = false;
		try {
			consumer.accept(session);
			if (transaction.getStatus() == TransactionStatus.ACTIVE) {
				transaction.commit();
			} else {
				rollingBack = true;
				transaction.rollback();
			}
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
