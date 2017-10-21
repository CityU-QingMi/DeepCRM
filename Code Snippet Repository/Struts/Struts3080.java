	public void setAttribute(final String name, final Object attribute) {

		if (name == null) {
			throw new NullPointerException(Localizer
					.getMessage("jsp.error.attribute.null_name"));
		}

		if (SecurityUtil.isPackageProtectionEnabled()) {
			AccessController.doPrivileged(new PrivilegedAction() {
				public Object run() {
					doSetAttribute(name, attribute);
					return null;
				}
			});
		} else {
			doSetAttribute(name, attribute);
		}
	}
