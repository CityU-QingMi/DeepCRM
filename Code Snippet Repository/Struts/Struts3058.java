	public int getAttributesScope(final String name) {

		if (name == null) {
			throw new NullPointerException(Localizer
					.getMessage("jsp.error.attribute.null_name"));
		}

		if (SecurityUtil.isPackageProtectionEnabled()) {
			return ((Integer) AccessController
					.doPrivileged(new PrivilegedAction() {
						public Object run() {
							return new Integer(doGetAttributeScope(name));
						}
					})).intValue();
		} else {
			return doGetAttributeScope(name);
		}
	}
