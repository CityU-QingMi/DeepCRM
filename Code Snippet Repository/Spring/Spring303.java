	@Before(value="", argNames="")
	public void checkNotLocked(
		Lockable mixin)  // Bind to arg
	{
		// Can also obtain the mixin (this) this way
		//Lockable mixin = (Lockable) jp.getThis();
		if (mixin.locked()) {
			throw new IllegalStateException();
		}
	}
