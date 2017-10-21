	public boolean onDelete(Session session) throws CallbackException {
		deleted=true;
		try {
			session.delete(foo);
		}
		catch (Exception e) {
			throw new CallbackException(e);
		}
		//if (child!=null) session.delete(child);
		return NO_VETO;
	}
