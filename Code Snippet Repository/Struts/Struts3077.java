	public Object getAttribute(final String name) {

		if (name == null) {
			throw new NullPointerException(Localizer
					.getMessage("jsp.error.attribute.null_name"));
		}

		if (SecurityUtil.isPackageProtectionEnabled()) {
			return AccessController.doPrivileged(new PrivilegedAction() {
				public Object run() {
					return doGetAttribute(name);
				}
			});
		} else {
			return doGetAttribute(name);
		}

	}
