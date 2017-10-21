    public static Method getReadMethod(Class beanClass, String prop)
            throws JasperException {

        Method method = null;
        Class type = null;
        try {
            java.beans.BeanInfo info
                    = java.beans.Introspector.getBeanInfo(beanClass);
            if (info != null) {
                java.beans.PropertyDescriptor pd[]
                        = info.getPropertyDescriptors();
                for (int i = 0; i < pd.length; i++) {
                    if (pd[i].getName().equals(prop)) {
                        method = pd[i].getReadMethod();
                        type = pd[i].getPropertyType();
                        break;
                    }
                }
            } else {
                // just in case introspection silently fails.
                throw new JasperException(
                        Localizer.getMessage("jsp.error.beans.nobeaninfo",
                                beanClass.getName()));
            }
        } catch (Exception ex) {
            throw new JasperException(ex);
        }
        if (method == null) {
            if (type == null) {
                throw new JasperException(
                        Localizer.getMessage("jsp.error.beans.noproperty", prop,
                                beanClass.getName()));
            } else {
                throw new JasperException(
                        Localizer.getMessage("jsp.error.beans.nomethod", prop,
                                beanClass.getName()));
            }
        }

        return method;
    }
