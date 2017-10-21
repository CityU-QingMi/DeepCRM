	public Object shallowCopy() {
		return AccessController.doPrivileged(
				new PrivilegedAction() {
					@Override
					public Object run() {
						return copyListeners();
					}
				}
		);
	}
