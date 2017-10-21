    public Class getBeanType(String bean)
        throws JasperException {
        Class clazz = null;
        try {
            clazz = loader.loadClass(beanTypes.get(bean));
        } catch (ClassNotFoundException ex) {
            throw new JasperException (ex);
        }
        return clazz;
    }
