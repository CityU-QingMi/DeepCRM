	public void forward(final String relativeUrlPath) throws ServletException,
			IOException {
		if (SecurityUtil.isPackageProtectionEnabled()) {
			try {
				AccessController.doPrivileged(new PrivilegedExceptionAction() {
					public Object run() throws Exception {
						doForward(relativeUrlPath);
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
			doForward(relativeUrlPath);
		}
	}
