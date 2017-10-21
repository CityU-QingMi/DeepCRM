    @Override
    public void setClassLoader(ClassLoader classLoader)
    {
        super.setClassLoader(classLoader);

        String name = getDisplayName();
        if (name==null) 
            name=getContextPath();
        
        if (classLoader!=null && classLoader instanceof WebAppClassLoader && getDisplayName()!=null)
            ((WebAppClassLoader)classLoader).setName(name);
    }
