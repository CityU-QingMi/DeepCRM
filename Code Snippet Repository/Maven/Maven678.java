    public MavenExecutionPlan( List<ExecutionPlanItem> planItem, DefaultLifecycles defaultLifecycles )
    {
        this.planItem = planItem;

        lastMojoExecutionForAllPhases = new LinkedHashMap<>();

        LinkedHashSet<String> totalPhaseSet = new LinkedHashSet<>();
        if ( defaultLifecycles != null )
        {
            for ( String phase : getDistinctPhasesInOrderOfExecutionPlanAppearance( planItem ) )
            {
                final Lifecycle lifecycle = defaultLifecycles.get( phase );
                if ( lifecycle != null )
                {
                    totalPhaseSet.addAll( lifecycle.getPhases() );
                }
            }
        }
        this.phasesInExecutionPlan = new ArrayList<>( totalPhaseSet );

        Map<String, ExecutionPlanItem> lastInExistingPhases = new HashMap<>();
        for ( ExecutionPlanItem executionPlanItem : getExecutionPlanItems() )
        {
            lastInExistingPhases.put( executionPlanItem.getLifecyclePhase(), executionPlanItem );
        }

        ExecutionPlanItem lastSeenExecutionPlanItem = null;

        for ( String phase : totalPhaseSet )
        {
            ExecutionPlanItem forThisPhase = lastInExistingPhases.get( phase );
            if ( forThisPhase != null )
            {
                lastSeenExecutionPlanItem = forThisPhase;
            }

            lastMojoExecutionForAllPhases.put( phase, lastSeenExecutionPlanItem );
        }
    }
