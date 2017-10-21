    private Model interpolateModel( Model model, ModelBuildingRequest request, ModelProblemCollector problems )
    {
        // save profile activations before interpolation, since they are evaluated with limited scope
        Map<String, Activation> originalActivations = getProfileActivations( model, true );

        Model interpolatedModel =
            modelInterpolator.interpolateModel( model, model.getProjectDirectory(), request, problems );
        if ( interpolatedModel.getParent() != null )
        {
            StringSearchInterpolator ssi = new StringSearchInterpolator();
            ssi.addValueSource( new MapBasedValueSource( request.getUserProperties() ) );

            ssi.addValueSource( new MapBasedValueSource( model.getProperties() ) );

            ssi.addValueSource( new MapBasedValueSource( request.getSystemProperties() ) );

            try
            {
                String interpolated = ssi.interpolate( interpolatedModel.getParent().getVersion() );
                interpolatedModel.getParent().setVersion( interpolated );
            }
            catch ( Exception e )
            {
                ModelProblemCollectorRequest mpcr =
                    new ModelProblemCollectorRequest( Severity.ERROR,
                                                      Version.BASE ).setMessage( "Failed to interpolate field: "
                                                          + interpolatedModel.getParent().getVersion()
                                                          + " on class: " ).setException( e );
                problems.add( mpcr );
            }

            
        }
        interpolatedModel.setPomFile( model.getPomFile() );

        // restore profiles with file activation to their value before full interpolation
        injectProfileActivations( model, originalActivations );

        return interpolatedModel;
    }
