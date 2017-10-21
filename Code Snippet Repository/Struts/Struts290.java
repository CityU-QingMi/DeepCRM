    public boolean shouldCreateIfNew(Class parentClass, String property,
                                     Object target, String keyProperty, boolean isIndexAccessed) {
        try {
            System.out.println("ognl:"+OgnlRuntime.getPropertyAccessor(Map.class)+" this:"+this);
        } catch (OgnlException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return isShouldCreateIfNew();
    }
