	public Object getAttribute(final String name, final int scope) {

		if (name == null) {
			throw new NullPointerException(Localizer
					.getMessage("jsp.error.attribute.null_name"));
		}

		if (SecurityUtil.isPackageProtectionEnabled()) {
			return AccessController.doPrivileged(new PrivilegedAction() {
				public Object run() {
					return doGetAttribute(name, scope);
				}
			});
		} else {
			return doGetAttribute(name, scope);
		}

	}
