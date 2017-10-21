	public void removeAttribute(final String name, final int scope) {

		if (name == null) {
			throw new NullPointerException(Localizer
					.getMessage("jsp.error.attribute.null_name"));
		}
		if (SecurityUtil.isPackageProtectionEnabled()) {
			AccessController.doPrivileged(new PrivilegedAction() {
				public Object run() {
					doRemoveAttribute(name, scope);
					return null;
				}
			});
		} else {
			doRemoveAttribute(name, scope);
		}
	}
