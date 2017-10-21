	public Object findAttribute(final String name) {
		if (SecurityUtil.isPackageProtectionEnabled()) {
			return AccessController.doPrivileged(new PrivilegedAction() {
				public Object run() {
					if (name == null) {
						throw new NullPointerException(Localizer
								.getMessage("jsp.error.attribute.null_name"));
					}

					return doFindAttribute(name);
				}
			});
		} else {
			if (name == null) {
				throw new NullPointerException(Localizer
						.getMessage("jsp.error.attribute.null_name"));
			}

			return doFindAttribute(name);
		}
	}
