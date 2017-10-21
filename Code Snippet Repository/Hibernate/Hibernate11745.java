	public static <T> T withTxSessionApply(JtaPlatform jtaPlatform, SessionBuilder sessionBuilder, ThrowingFunction<Session, T, Exception> function) throws Exception {
		if (jtaPlatform != null) {
			TransactionManager tm = jtaPlatform.retrieveTransactionManager();
			Callable<T> callable = () -> withSessionApply(sessionBuilder, s -> {
				T t = function.apply(s);
				s.flush();
				return t;
			});
			return Caches.withinTx(tm, callable);
		} else {
			return withSessionApply(sessionBuilder, s -> withResourceLocalTx(s, function));
		}
	}
