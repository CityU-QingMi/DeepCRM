    public static boolean containsBean(Object beanFactory, String beanId) {
        try {
            Method getBeanMethod = beanFactory.getClass().getMethod("containsBean", String.class);
            return (Boolean) getBeanMethod.invoke(beanFactory, beanId);
        } catch (Exception ex) {
            LOG.error("Unable to call containsBean() on object of type [{}], with bean id [{}]", beanFactory.getClass().getName(), beanId, ex);
        }

        return false;
    }
