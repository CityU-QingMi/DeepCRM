	public boolean putFromLoad(SharedSessionContractImplementor session, Object key, Object value, long txTimestamp, Object version) throws CacheException {
		while (true) {
			Lockable item = (Lockable) region.get( key );

			if (item == null) {
/**/
/**/
/**/
/**/
/**/
/**/
				if (region.putIfAbsent( key, new Item( value, version, txTimestamp, nextItemId() ))) {
					return true;
				}
			}
			else if (item.isWriteable( txTimestamp, version, versionComparator )) {
				if (region.replace( key, item, new Item( value, version, txTimestamp, nextItemId() ))) {
					return true;
				}
			}
			else {
				return false;
			}
		}
	}
