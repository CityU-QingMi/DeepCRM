    public String write(Object object, Collection<Pattern> excludeProperties,
                        Collection<Pattern> includeProperties, boolean excludeNullProperties) throws JSONException {
        this.excludeNullProperties = excludeNullProperties;
        this.buf.setLength(0);
        this.root = object;
        this.exprStack = "";
        this.buildExpr = ((excludeProperties != null) && !excludeProperties.isEmpty())
                || ((includeProperties != null) && !includeProperties.isEmpty());
        this.excludeProperties = excludeProperties;
        this.includeProperties = includeProperties;
        this.value(object, null);

        return this.buf.toString();
    }
