    public ToolchainsBuildingException( List<Problem> problems )
    {
        super( toMessage( problems ) );

        this.problems = new ArrayList<>();
        if ( problems != null )
        {
            this.problems.addAll( problems );
        }
    }
