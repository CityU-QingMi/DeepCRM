    public void addInjections (WebAppContext context, Descriptor descriptor, XmlParser.Node node, String jndiName, Class<?> valueClass)
    {
        Iterator<XmlParser.Node>  itor = node.iterator("injection-target");

        while(itor.hasNext())
        {
            XmlParser.Node injectionNode = itor.next();
            String targetClassName = injectionNode.getString("injection-target-class", false, true);
            String targetName = injectionNode.getString("injection-target-name", false, true);
            if ((targetClassName==null) || targetClassName.equals(""))
            {
                LOG.warn("No classname found in injection-target");
                continue;
            }
            if ((targetName==null) || targetName.equals(""))
            {
                LOG.warn("No field or method name in injection-target");
                continue;
            }

            InjectionCollection injections = (InjectionCollection)context.getAttribute(InjectionCollection.INJECTION_COLLECTION);
            if (injections == null)
            {
                injections = new InjectionCollection();
                context.setAttribute(InjectionCollection.INJECTION_COLLECTION, injections);
            }
            // comments in the javaee_5.xsd file specify that the targetName is looked
            // for first as a java bean property, then if that fails, as a field
            try
            {
                Class<?> clazz = context.loadClass(targetClassName);
                Injection injection = new Injection();
                injection.setJndiName(jndiName);
                injection.setTarget(clazz, targetName, valueClass);
                injections.add(injection);

                //Record which was the first descriptor to declare an injection for this name
                if (context.getMetaData().getOriginDescriptor(node.getTag()+"."+jndiName+".injection") == null)
                    context.getMetaData().setOrigin(node.getTag()+"."+jndiName+".injection", descriptor);
            }
            catch (ClassNotFoundException e)
            {
                LOG.warn("Couldn't load injection target class "+targetClassName);
            }
        }
    }
