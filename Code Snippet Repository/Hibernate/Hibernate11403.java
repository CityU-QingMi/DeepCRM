	public void releasePutFromLoadLock(Object key, Lock lock) {
		if (trace) {
			log.tracef("releasePutFromLoadLock(%s#%s, %s)", cache.getName(), key, lock);
		}
		final PendingPutMap pending = (PendingPutMap) lock;
		if ( pending != null ) {
			if ( pending.canRemove() ) {
				pending.setRemoved();
				pendingPuts.remove( key, pending );
			}
			pending.releaseLock();
		}
	}
