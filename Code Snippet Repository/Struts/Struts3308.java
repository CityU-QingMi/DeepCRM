    protected Method findBaseAccessor(Class clazz, Method accessor) {
        Method baseAccessor = null;
        if (clazz.getName().contains("$$EnhancerByCGLIB$$")) {
            try {
                baseAccessor = Thread.currentThread().getContextClassLoader().loadClass(
                        clazz.getName().substring(0, clazz.getName().indexOf("$$"))).getMethod(
                        accessor.getName(), accessor.getParameterTypes());
            } catch (Exception ex) {
                LOG.debug(ex.getMessage(), ex);
            }
        } else if (clazz.getName().contains("$$_javassist")) {
            try {
                baseAccessor = Class.forName(
                        clazz.getName().substring(0, clazz.getName().indexOf("_$$")))
                        .getMethod(accessor.getName(), accessor.getParameterTypes());
            } catch (Exception ex) {
                LOG.debug(ex.getMessage(), ex);
            }
            
        //in hibernate4.3.7,because javassist3.18.1's class name generate rule is '_$$_jvst'+...
        } else if(clazz.getName().contains("$$_jvst")){
            try {
                baseAccessor = Class.forName(
                        clazz.getName().substring(0, clazz.getName().indexOf("_$$")))
                        .getMethod(accessor.getName(), accessor.getParameterTypes());
            } catch (Exception ex) {
                LOG.debug(ex.getMessage(), ex);
            }
        }
        else {
            return accessor;
        }
        return baseAccessor;
    }
