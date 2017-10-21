    public static void flushAuthenticationUserCache(String userName) {                                
        ApplicationContext ctx = 
            WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		try {
			UserCache userCache = (UserCache) ctx.getBean("userCache");
			if (userCache != null) {
				userCache.removeUserFromCache(userName);
			}
		} catch (NoSuchBeanDefinitionException exc) {
			log.debug("No userCache bean in context", exc);
		}
    }
