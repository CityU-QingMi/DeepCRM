    public Object instantiate(
            RootBeanDefinition beanDefinition, String beanName, BeanFactory owner) {

        // Don't override the class with CGLIB if no overrides.
        if (beanDefinition.getMethodOverrides().isEmpty()) {
            Class clazz = beanDefinition.getBeanClass();
            if (clazz.isInterface()) {
                throw new BeanInstantiationException(clazz, "Specified class is an interface");
            }
            try {
                Constructor constructor = clazz.getDeclaredConstructor((Class[]) null);
                return BeanUtils.instantiateClass(constructor, null);
            }
            catch (Exception ex) {
                throw new BeanInstantiationException(clazz, "No default constructor found", ex);
            }
        } else {
            // Must generate CGLIB subclass.
            return instantiateWithMethodInjection(beanDefinition, beanName, owner);
        }
    }
