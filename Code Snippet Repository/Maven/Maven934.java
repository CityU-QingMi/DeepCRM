    public String getPomLocation()
    {
        if ( getPomFile() != null )
        {
            return getPomFile().getAbsolutePath();
        }
        else
        {
            return "null";
        }
    }
