	@Override
	public Object visitSizeCommand(InvocationContext ctx, SizeCommand command) throws Throwable {
		Set<Flag> flags = command.getFlags();
		int size = 0;
		AdvancedCache decoratedCache = cache.getAdvancedCache();
		if (flags != null) {
			decoratedCache = decoratedCache.withFlags(flags.toArray(new Flag[flags.size()]));
		}
		// In non-transactional caches we don't care about context
		CloseableIterable<CacheEntry<Object, Void>> iterable = decoratedCache
				.filterEntries(VersionedEntry.EXCLUDE_EMPTY_EXTRACT_VALUE).converter(NullValueConverter.getInstance());
		try {
			for (CacheEntry<Object, Void> entry : iterable) {
				if (size++ == Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				}
			}
		}
		finally {
			iterable.close();
		}
		return size;
	}
