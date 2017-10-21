    public void doHandle (Class<?> clazz)
    {
        Resources resources = (Resources)clazz.getAnnotation(Resources.class);
        if (resources != null)
        {
            Resource[] resArray = resources.value();
            if (resArray==null||resArray.length==0)
            {
                LOG.warn ("Skipping empty or incorrect Resources annotation on "+clazz.getName());
                return;
            }

            for (int j=0;j<resArray.length;j++)
            {
                String name = resArray[j].name();
                String mappedName = resArray[j].mappedName();

                if (name==null || name.trim().equals(""))
                    throw new IllegalStateException ("Class level Resource annotations must contain a name (Common Annotations Spec Section 2.3)");

                try
                {
                    //TODO don't ignore the shareable, auth etc etc

                    if (!org.eclipse.jetty.plus.jndi.NamingEntryUtil.bindToENC(_wac, name, mappedName))
                        if (!org.eclipse.jetty.plus.jndi.NamingEntryUtil.bindToENC(_wac.getServer(), name, mappedName))
                            LOG.warn("Skipping Resources(Resource) annotation on "+clazz.getName()+" for name "+name+": No resource bound at "+(mappedName==null?name:mappedName));
                }
                catch (NamingException e)
                {
                    LOG.warn(e);
                }
            }
        }
    }    
