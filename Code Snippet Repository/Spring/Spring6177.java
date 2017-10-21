	public static void cleanupParameters(@Nullable Collection<?> paramValues) {
		if (paramValues != null) {
			for (Object inValue : paramValues) {
				if (inValue instanceof DisposableSqlTypeValue) {
					((DisposableSqlTypeValue) inValue).cleanup();
				}
				else if (inValue instanceof SqlValue) {
					((SqlValue) inValue).cleanup();
				}
			}
		}
	}
