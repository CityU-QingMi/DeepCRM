    @Override
    public void add( ModelProblemCollectorRequest req )
    {
        int line = -1;
        int column = -1;
        String source = null;
        String modelId = null;

        if ( req.getLocation() != null )
        {
            line = req.getLocation().getLineNumber();
            column = req.getLocation().getColumnNumber();
            if ( req.getLocation().getSource() != null )
            {
                modelId = req.getLocation().getSource().getModelId();
                source = req.getLocation().getSource().getLocation();
            }
        }

        if ( modelId == null )
        {
            modelId = getModelId();
            source = getSource();
        }

        if ( line <= 0 && column <= 0 && req.getException() instanceof ModelParseException )
        {
            ModelParseException e = (ModelParseException) req.getException();
            line = e.getLineNumber();
            column = e.getColumnNumber();
        }

        ModelProblem problem =
            new DefaultModelProblem( req.getMessage(), req.getSeverity(), req.getVersion(), source, line, column,
                                     modelId, req.getException() );

        add( problem );
    }
