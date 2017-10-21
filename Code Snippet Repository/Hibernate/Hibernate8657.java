	public boolean onSave(Session session) throws CallbackException {
		created=true;
		try {
			foo = new Foo();
			session.save(foo);
		}
		catch (Exception e) {
			throw new CallbackException(e);
		}
		foo.setString("child of a qux");
		return NO_VETO;
	}
