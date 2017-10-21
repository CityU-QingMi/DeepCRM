    protected Resource findWebInfClassesDir (WebAppContext context)
    throws Exception
    {
        if (context == null)
            return null;
        
        Resource web_inf = context.getWebInf();

        // Find WEB-INF/classes
        if (web_inf != null && web_inf.isDirectory())
        {
            // Look for classes directory
            Resource classes= web_inf.addPath("classes/");
            if (classes.exists())
                return classes;
        }
        return null;
    }
