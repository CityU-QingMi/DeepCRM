		public Object getSubject(HttpServletRequest request) {
			try {
				ServletContext servletContext = request.getServletContext();
				Object securityContext = securityContextMethod.invoke(servletContext);
				Object subject = currentUserMethod.invoke(null, securityContext, request);
				if (subject == null) {
					Object securityProvider = providerMethod.invoke(null);
					subject = anonymousSubjectMethod.invoke(securityProvider);
				}
				return subject;
			}
			catch (Exception ex) {
				throw new HandshakeFailureException("Failed to obtain SubjectHandle", ex);
			}
		}
