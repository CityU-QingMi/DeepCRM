    public void setExportedArtifacts( List<String> exportedArtifacts )
    {
        if ( exportedArtifacts == null )
        {
            this.exportedArtifacts = null;
        }
        else
        {
            this.exportedArtifacts = new ArrayList<>( exportedArtifacts );
        }
    }
