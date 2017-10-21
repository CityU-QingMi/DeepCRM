    @Override
    public void doStart() throws Exception
    {
        Context scontext = ContextHandler.getCurrentContext();
        _context = (scontext == null?null:scontext.getContextHandler());
        if (_mimeTypes==null)
            _mimeTypes = _context == null?new MimeTypes():_context.getMimeTypes();

        _resourceService.setContentFactory(new ResourceContentFactory(this,_mimeTypes,_resourceService.getPrecompressedFormats()));
        _resourceService.setWelcomeFactory(this);

        super.doStart();
    }
