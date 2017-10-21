    public SettingsBuildingException( List<SettingsProblem> problems )
    {
        super( toMessage( problems ) );

        this.problems = new ArrayList<>();
        if ( problems != null )
        {
            this.problems.addAll( problems );
        }
    }
