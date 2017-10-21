	@Override
	public Object visitPutKeyValueCommand(InvocationContext ctx, PutKeyValueCommand command) throws Throwable {
		MVCCEntry e = (MVCCEntry) ctx.lookupEntry(command.getKey());
		if (e == null) {
			return null;
		}
		log.tracef("In cache %s(%d) applying update %s to %s", cache.getName(), region.getLastRegionInvalidation(), command.getValue(), e.getValue());
		try {
			Object value = command.getValue();
			if (value instanceof TombstoneUpdate) {
				return handleTombstoneUpdate(e, (TombstoneUpdate) value, command);
			}
			else if (value instanceof Tombstone) {
				return handleTombstone(e, (Tombstone) value);
			}
			else if (value instanceof FutureUpdate) {
				return handleFutureUpdate(e, (FutureUpdate) value, command);
			}
			else {
				return super.visitPutKeyValueCommand(ctx, command);
			}
		}
		finally {
			log.tracef("Result is %s", e.getValue());
		}
	}
