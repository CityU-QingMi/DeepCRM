    public static Object getBean(Object beanFactory, String beanId) {
        try {
            Method getBeanMethod = beanFactory.getClass().getMethod("getBean", String.class);
            return getBeanMethod.invoke(beanFactory, beanId);
        } catch (Exception ex) {
            LOG.error("Unable to call getBean() on object of type [{}], with bean id [{}]",  beanFactory.getClass().getName(), beanId, ex);
        }

        return null;
    }
