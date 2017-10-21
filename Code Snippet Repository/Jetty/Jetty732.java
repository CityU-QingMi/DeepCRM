    public ContextHandler getContextHandler() throws Exception
    {
        if (_context == null)
        {
            _context = getAppProvider().createContextHandler(this);
            
            AttributesMap attributes = _manager.getContextAttributes();
            if (attributes!=null && attributes.size()>0)
            {
                // Merge the manager attributes under the existing attributes
                attributes = new AttributesMap(attributes);
                attributes.addAll(_context.getAttributes());
                _context.setAttributes(attributes);
            }
        }
        return _context;
    }
