	public void handlePageException(final Throwable t) throws IOException,
			ServletException {
		if (t == null)
			throw new NullPointerException("null Throwable");

		if (SecurityUtil.isPackageProtectionEnabled()) {
			try {
				AccessController.doPrivileged(new PrivilegedExceptionAction() {
					public Object run() throws Exception {
						doHandlePageException(t);
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
			doHandlePageException(t);
		}

	}
