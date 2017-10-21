    public void setExportedPackages( List<String> exportedPackages )
    {
        if ( exportedPackages == null )
        {
            this.exportedPackages = null;
        }
        else
        {
            this.exportedPackages = new ArrayList<>( exportedPackages );
        }
    }
