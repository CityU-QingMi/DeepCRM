    public void visitListener(WebAppContext context, Descriptor descriptor, XmlParser.Node node)
    {
        String className = node.getString("listener-class", false, true);
        EventListener listener = null;
        try
        {
            if (className != null && className.length()> 0)
            {
                //Servlet Spec 3.0 p 74
                //Duplicate listener declarations don't result in duplicate listener instances
                EventListener[] listeners=context.getEventListeners();
                if (listeners!=null)
                {
                    for (EventListener l : listeners)
                    {
                        if (l.getClass().getName().equals(className))
                            return;
                    }
                }

                ((WebDescriptor)descriptor).addClassName(className);

                Class<? extends EventListener> listenerClass = (Class<? extends EventListener>)context.loadClass(className);
                listener = newListenerInstance(context,listenerClass, descriptor);
                if (!(listener instanceof EventListener))
                {
                    LOG.warn("Not an EventListener: " + listener);
                    return;
                }
                context.addEventListener(listener);
                context.getMetaData().setOrigin(className+".listener", descriptor);

            }
        }
        catch (Exception e)
        {
            LOG.warn("Could not instantiate listener " + className, e);
            return;
        }
    }
