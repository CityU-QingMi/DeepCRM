    @Override
    @SuppressWarnings("")
    public EventState getState(long timestamp)
    {
       EventState state = new EventState();
       
       for (EventTrigger trigger : _triggers)
       {
           EventState subState = trigger.getState(timestamp);
           if (subState!=null)
           {
               state.addAll(subState.values());
           }
       }
       
       return state;
    }
