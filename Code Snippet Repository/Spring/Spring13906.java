		public SubjectHelper() {
			try {
				String className = "weblogic.servlet.internal.WebAppServletContext";
				securityContextMethod = method(className, "getSecurityContext");

				className = "weblogic.servlet.security.internal.SecurityModule";
				currentUserMethod = method(className, "getCurrentUser",
						type("weblogic.servlet.security.internal.ServletSecurityContext"),
						HttpServletRequest.class);

				className = "weblogic.servlet.security.internal.WebAppSecurity";
				providerMethod = method(className, "getProvider");
				anonymousSubjectMethod = providerMethod.getReturnType().getDeclaredMethod("getAnonymousSubject");
			}
			catch (Exception ex) {
				throw new IllegalStateException("No compatible WebSocket version found", ex);
			}
		}
