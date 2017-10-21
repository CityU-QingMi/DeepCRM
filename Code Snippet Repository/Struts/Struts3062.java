	public Enumeration<String> getAttributeNamesInScope(final int scope) {
		if (SecurityUtil.isPackageProtectionEnabled()) {
			return (Enumeration) AccessController
					.doPrivileged(new PrivilegedAction() {
						public Object run() {
							return doGetAttributeNamesInScope(scope);
						}
					});
		} else {
			return doGetAttributeNamesInScope(scope);
		}
	}
