    protected Class resolveBeanClass(RootBeanDefinition mbd, String beanName, Class[] typesToMatch) {
        try {
            if (typesToMatch != null) {
                ClassLoader tempClassLoader = getTempClassLoader();
                if (tempClassLoader != null) {
                    if (tempClassLoader instanceof DecoratingClassLoader) {
                        DecoratingClassLoader dcl = (DecoratingClassLoader) tempClassLoader;
                        for (int i = 0; i < typesToMatch.length; i++) {
                            dcl.excludeClass(typesToMatch[i].getName());
                        }
                    }
                    String className = mbd.getBeanClassName();
                    return (className != null ? ClassUtils.forName(className, tempClassLoader) : null);
                }
            }
            return mbd.resolveBeanClass(getBeanClassLoader());
        }
        catch (ClassNotFoundException ex) {
            throw new CannotLoadBeanClassException(mbd.getResourceDescription(), beanName, mbd.getBeanClassName(), ex);
        }
        catch (LinkageError err) {
            throw new CannotLoadBeanClassException(mbd.getResourceDescription(), beanName, mbd.getBeanClassName(), err);
        }
    }
