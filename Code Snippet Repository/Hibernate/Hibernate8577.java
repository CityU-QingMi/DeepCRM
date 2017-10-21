	public boolean onSave(Session s) throws CallbackException {
		if (friends==null) return false;
		try {
			Iterator iter = friends.iterator();
			while ( iter.hasNext() ) {
				s.save( iter.next() );
			}
		}
		catch (Exception e) {
			throw new CallbackException(e);
		}
		return false;
	}
