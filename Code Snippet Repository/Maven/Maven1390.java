    public Model getModel()
    {
        if ( result == null )
        {
            return null;
        }
        if ( result.getEffectiveModel() != null )
        {
            return result.getEffectiveModel();
        }
        return result.getRawModel();
    }
