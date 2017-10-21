	public static void withTxSession(JtaPlatform jtaPlatform, SessionBuilder sessionBuilder, ThrowingConsumer<Session, Exception> consumer) throws Exception {
		if (jtaPlatform != null) {
			TransactionManager tm = jtaPlatform.retrieveTransactionManager();
			final SessionBuilder sb = sessionBuilder;
			Caches.withinTx(tm, () -> {
				withSession(sb, s -> {
					consumer.accept(s);
					// we need to flush the session before close when running with JTA transactions
					s.flush();
				});
				return null;
			});
		} else {
			withSession(sessionBuilder, s -> withResourceLocalTx(s, consumer));
		}
	}
