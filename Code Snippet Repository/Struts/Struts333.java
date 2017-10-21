    @Override
    public Object getProperty(Map context, Object target, Object name) throws OgnlException {

        if (ReflectionContextState.isGettingByKeyProperty(context)
                || name.equals(XWorkCollectionPropertyAccessor.KEY_PROPERTY_FOR_CREATION)) {
            return _sAcc.getProperty(context, target, name);
        } else if (name instanceof String) {
            return super.getProperty(context, target, name);
        }
        ReflectionContextState.updateCurrentPropertyPath(context, name);
        Class lastClass = (Class) context.get(XWorkConverter.LAST_BEAN_CLASS_ACCESSED);
        String lastProperty = (String) context.get(XWorkConverter.LAST_BEAN_PROPERTY_ACCESSED);
        
        if (name instanceof Number
                && ReflectionContextState.isCreatingNullObjects(context)
                && objectTypeDeterminer.shouldCreateIfNew(lastClass,lastProperty,target,null,true)) {

            List list = (List) target;
            int index = ((Number) name).intValue();
            int listSize = list.size();

            if (lastClass == null || lastProperty == null) {
                return super.getProperty(context, target, name);
            }
            Class beanClass = objectTypeDeterminer.getElementClass(lastClass, lastProperty, name);
            if (listSize <= index) {
                Object result;

                for (int i = listSize; i < index; i++) {
                    list.add(null);
                }
                try {
                    list.add(index, result = objectFactory.buildBean(beanClass, context));
                } catch (Exception exc) {
                    throw new XWorkException(exc);
                }
                return result;
            } else if (list.get(index) == null) {
                Object result;
                try {
                    list.set(index, result = objectFactory.buildBean(beanClass, context));
                } catch (Exception exc) {
                    throw new XWorkException(exc);
                }
                return result;
            }
        }
        return super.getProperty(context, target, name);
    }
