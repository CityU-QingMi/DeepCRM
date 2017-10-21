    public TypeConverter getTypeConverter( Map<String, Object> context )
    {
        Object obj = context.get(TypeConverter.TYPE_CONVERTER_CONTEXT_KEY);
        if (obj instanceof TypeConverter) {
            return (TypeConverter) obj;
            
        // for backwards-compatibility
        } else if (obj instanceof ognl.TypeConverter) {
            return new XWorkTypeConverterWrapper((ognl.TypeConverter) obj);
        }
        return null; 
    }
