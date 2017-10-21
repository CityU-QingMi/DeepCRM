		public static Connection doGetConnection(Session session) {
			try {
				Method methodToUse = connectionMethodToUse;
				if (methodToUse == null) {
					// Reflective lookup to find SessionImpl's connection() method on Hibernate 4.x/5.x
					methodToUse = session.getClass().getMethod("connection");
					connectionMethodToUse = methodToUse;
				}
				Connection con = (Connection) ReflectionUtils.invokeMethod(methodToUse, session);
				Assert.state(con != null, "No Connection from Session");
				return con;
			}
			catch (NoSuchMethodException ex) {
				throw new IllegalStateException("Cannot find connection() method on Hibernate Session", ex);
			}
		}
