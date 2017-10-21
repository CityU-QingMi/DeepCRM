    private Relocation getRelocation( Model model )
    {
        Relocation relocation = null;
        DistributionManagement distMgmt = model.getDistributionManagement();
        if ( distMgmt != null )
        {
            relocation = distMgmt.getRelocation();
        }
        return relocation;
    }
