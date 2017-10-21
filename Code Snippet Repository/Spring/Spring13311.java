		@Override
		public ServletRegistration.Dynamic addServlet(String servletName, Servlet servlet) {
			if (servlets.containsKey(servletName)) {
				return null;
			}
			servlets.put(servletName, servlet);
			MockServletRegistration registration = new MockServletRegistration();
			servletRegistrations.put(servletName, registration);
			return registration;
		}
