     public void handleClass (Class<?> clazz)
     {
         Resource resource = (Resource)clazz.getAnnotation(Resource.class);
         if (resource != null)
         {
             String name = resource.name();
             String mappedName = resource.mappedName();

             if (name==null || name.trim().equals(""))
                 throw new IllegalStateException ("Class level Resource annotations must contain a name (Common Annotations Spec Section 2.3)");

             try
             {
                 if (!org.eclipse.jetty.plus.jndi.NamingEntryUtil.bindToENC(_context, name,mappedName))
                     if (!org.eclipse.jetty.plus.jndi.NamingEntryUtil.bindToENC(_context.getServer(), name,mappedName))
                         throw new IllegalStateException("No resource at "+(mappedName==null?name:mappedName));
             }
             catch (NamingException e)
             {
                 LOG.warn(e);
             }
         }
    }
