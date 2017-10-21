    public static void clear(Map<String, Object> context) {
        if (context != null) {
            context.put(XWorkConverter.LAST_BEAN_CLASS_ACCESSED,null);
            context.put(XWorkConverter.LAST_BEAN_PROPERTY_ACCESSED,null);
    
            context.put(CURRENT_PROPERTY_PATH,null);
            context.put(FULL_PROPERTY_PATH,null);
        }

    }
