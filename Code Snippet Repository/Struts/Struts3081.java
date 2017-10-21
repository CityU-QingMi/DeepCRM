	public void setAttribute(final String name, final Object o, final int scope) {

		if (name == null) {
			throw new NullPointerException(Localizer
					.getMessage("jsp.error.attribute.null_name"));
		}

		if (SecurityUtil.isPackageProtectionEnabled()) {
			AccessController.doPrivileged(new PrivilegedAction() {
				public Object run() {
					doSetAttribute(name, o, scope);
					return null;
				}
			});
		} else {
			doSetAttribute(name, o, scope);
		}

	}
