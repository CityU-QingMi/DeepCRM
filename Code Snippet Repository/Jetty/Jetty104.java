    public void doHandle(Class clazz)
    {
        if (!Servlet.class.isAssignableFrom(clazz))
            return;
        
        MultipartConfig multi = (MultipartConfig) clazz.getAnnotation(MultipartConfig.class);
        if (multi == null)
            return;
        
        MetaData metaData = _context.getMetaData();
              
        //TODO: The MultipartConfigElement needs to be set on the ServletHolder's Registration.
        //How to identify the correct Servlet?  If the Servlet has no WebServlet annotation on it, does it mean that this MultipartConfig
        //annotation applies to all declared instances in web.xml/programmatically?
        //Assuming TRUE for now.

        ServletHolder holder = getServletHolderForClass(clazz);
        if (holder != null)
        {
            Descriptor d = metaData.getOriginDescriptor(holder.getName()+".servlet.multipart-config");
            //if a descriptor has already set the value for multipart config, do not 
            //let the annotation override it
            if (d == null)
            {
                metaData.setOrigin(holder.getName()+".servlet.multipart-config",multi,clazz);
                holder.getRegistration().setMultipartConfig(new MultipartConfigElement(multi));
            }
        }
    }
