    public void enter()
    {
        LinkedList<ScopeState> stack = values.get();
        if ( stack == null )
        {
            stack = new LinkedList<>();
            values.set( stack );
        }
        stack.addFirst( new ScopeState() );
    }
