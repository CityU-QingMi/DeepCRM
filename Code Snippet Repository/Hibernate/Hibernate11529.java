	protected void exec(boolean transactional, Callable<?>... callables) {
		try {
			if (transactional) {
				for (Callable<?> c : callables) {
					withTx(tm, c);
				}
			} else {
				for (Callable<?> c : callables) {
					c.call();
				}
			}
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
