        @Override
		@Nullable
		public String resolvePlaceholder(String placeholderName) {
            try {
                String propVal = this.servletContext.getInitParameter(placeholderName);
				if (propVal == null) {
					// Fall back to system properties.
					propVal = System.getProperty(placeholderName);
					if (propVal == null) {
						// Fall back to searching the system environment.
						propVal = System.getenv(placeholderName);
					}
				}
				return propVal;
			}
            catch (Throwable ex) {
                System.err.println("Could not resolve placeholder '" + placeholderName + "' in [" +
                        this.text + "] as ServletContext init-parameter or system property: " + ex);
                return null;
            }
        }
