    public void doHandle (Class clazz)
    {
        if (!Servlet.class.isAssignableFrom(clazz))
            return;

        javax.annotation.security.RunAs runAs = (javax.annotation.security.RunAs)clazz.getAnnotation(javax.annotation.security.RunAs.class);
        if (runAs != null)
        {
            String role = runAs.value();
            if (role != null)
            {
                ServletHolder holder = getServletHolderForClass(clazz);
                if (holder != null)
                {
                    MetaData metaData = _context.getMetaData();
                    Descriptor d = metaData.getOriginDescriptor(holder.getName()+".servlet.run-as");
                    //if a descriptor has already set the value for run-as, do not
                    //let the annotation override it
                    if (d == null)
                    {
                        metaData.setOrigin(holder.getName()+".servlet.run-as",runAs,clazz);
                        org.eclipse.jetty.plus.annotation.RunAs ra = new org.eclipse.jetty.plus.annotation.RunAs();
                        ra.setTargetClassName(clazz.getCanonicalName());
                        ra.setRoleName(role);
                        RunAsCollection raCollection = (RunAsCollection)_context.getAttribute(RunAsCollection.RUNAS_COLLECTION);
                        if (raCollection == null)
                        {
                            raCollection = new RunAsCollection();
                            _context.setAttribute(RunAsCollection.RUNAS_COLLECTION, raCollection);
                        }
                        raCollection.add(ra);
                    }
                }
            }
            else
                LOG.warn("Bad value for @RunAs annotation on class "+clazz.getName());
        }

    }
