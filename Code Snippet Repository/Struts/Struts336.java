    private Object getValue(Map context, Object value) {
         Class lastClass = (Class) context.get(XWorkConverter.LAST_BEAN_CLASS_ACCESSED);
         String lastProperty = (String) context.get(XWorkConverter.LAST_BEAN_PROPERTY_ACCESSED);
         if (lastClass == null || lastProperty == null) {
             return value;
         }
         Class elementClass = objectTypeDeterminer.getElementClass(lastClass, lastProperty, null);
         if (elementClass == null) {
             return value; // nothing is specified, we assume it will be the value passed in.
         }
         return xworkConverter.convertValue(context, value, elementClass);
    }
