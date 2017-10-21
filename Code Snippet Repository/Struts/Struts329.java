    @Override
    public Object getProperty(Map context, Object target, Object oname) throws OgnlException {
        if (target instanceof Parameter) {
            if ("value".equalsIgnoreCase(String.valueOf(oname))) {
                throw new OgnlException("Access to " + oname + " is not allowed! Call parameter name directly!");
            }
            return ((Parameter) target).getObject();
        }
        return super.getProperty(context, target, oname);
    }
