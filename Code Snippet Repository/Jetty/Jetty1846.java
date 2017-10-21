    @Override
    public void beanRemoved(Container parent, Object obj)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("beanRemoved {}->{}", parent, obj);

        if (parent == null)
            parent = ROOT;

        if (_beans.remove(obj, parent))
        {
            ObjectName objectName = _mbeans.remove(obj);
            if (objectName != null)
                unregister(objectName);
        }
    }
