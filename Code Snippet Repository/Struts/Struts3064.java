	public void removeAttribute(final String name) {

		if (name == null) {
			throw new NullPointerException(Localizer
					.getMessage("jsp.error.attribute.null_name"));
		}

		if (SecurityUtil.isPackageProtectionEnabled()) {
			AccessController.doPrivileged(new PrivilegedAction() {
				public Object run() {
					doRemoveAttribute(name);
					return null;
				}
			});
		} else {
			doRemoveAttribute(name);
		}
	}
