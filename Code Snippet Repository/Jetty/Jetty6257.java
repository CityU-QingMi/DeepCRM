    public static boolean isEnabledViaContext(ServletContext context, String keyName, boolean defValue)
    {
        // Try context parameters first
        String cp = context.getInitParameter(keyName);
    
        if(cp != null)
        {
            if (TypeUtil.isTrue(cp))
            {
                return true;
            }
        
            if (TypeUtil.isFalse(cp))
            {
                return false;
            }
        
            return defValue;
        }
    
        // Next, try attribute on context
        Object enable = context.getAttribute(keyName);
    
        if(enable != null)
        {
            if (TypeUtil.isTrue(enable))
            {
                return true;
            }
        
            if (TypeUtil.isFalse(enable))
            {
                return false;
            }
        }
    
        return defValue;
    }
