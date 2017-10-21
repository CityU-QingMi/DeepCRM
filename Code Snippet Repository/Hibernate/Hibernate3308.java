	@SuppressWarnings( {""})
	public Map.Entry[] entryArray() {
		if (dirty) {
			entryArray = new Map.Entry[ map.size() ];
			Iterator itr = map.entrySet().iterator();
			int i=0;
			while ( itr.hasNext() ) {
				Map.Entry me = (Map.Entry) itr.next();
				entryArray[i++] = new IdentityMapEntry( ( (IdentityKey) me.getKey() ).key, me.getValue() );
			}
			dirty = false;
		}
		return entryArray;
	}
