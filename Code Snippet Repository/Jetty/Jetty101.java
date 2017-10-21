    public void handle(ClassInfo classInfo)
    {
        try
        {
            for (int i=0; classInfo.getInterfaces() != null && i < classInfo.getInterfaces().length;i++)
            {
                addToInheritanceMap(classInfo.getInterfaces()[i], classInfo.getClassName());
                //_inheritanceMap.add (classInfo.getInterfaces()[i], classInfo.getClassName());
            }
            //To save memory, we don't record classes that only extend Object, as that can be assumed
            if (!"java.lang.Object".equals(classInfo.getSuperName()))
            {
                addToInheritanceMap(classInfo.getSuperName(), classInfo.getClassName());
                //_inheritanceMap.add(classInfo.getSuperName(), classInfo.getClassName());
            }
        }
        catch (Exception e)
        {
            LOG.warn(e);
        }  
    }
