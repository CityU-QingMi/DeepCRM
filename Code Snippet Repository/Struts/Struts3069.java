	public void include(final String relativeUrlPath, final boolean flush)
			throws ServletException, IOException {
		if (SecurityUtil.isPackageProtectionEnabled()) {
			try {
				AccessController.doPrivileged(new PrivilegedExceptionAction() {
					public Object run() throws Exception {
						doInclude(relativeUrlPath, flush);
						return null;
					}
				});
			} catch (PrivilegedActionException e) {
				Exception ex = e.getException();
				if (ex instanceof IOException) {
					throw (IOException) ex;
				} else {
					throw (ServletException) ex;
				}
			}
		} else {
			doInclude(relativeUrlPath, flush);
		}
	}
