    protected TypeConverter getTypeConverter(Object obj) {
        if (obj instanceof TypeConverter) {
            return (TypeConverter) obj;

            // For backwards compatibility
        } else if (obj instanceof ognl.TypeConverter) {
            return new XWorkTypeConverterWrapper((ognl.TypeConverter) obj);
        } else {
            throw new IllegalArgumentException("Type converter class " + obj.getClass() + " doesn't implement com.opensymphony.xwork2.conversion.TypeConverter");
        }
    }
