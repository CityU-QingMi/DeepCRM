    private boolean replaceHandler(Handler handler,Handler replace)
    {
        HandlerWrapper wrapper=this;
        while(true)
        {
            if (wrapper.getHandler()==handler)
            {
                doSetHandler(wrapper,replace);
                return true;
            }
            
            if (!(wrapper.getHandler() instanceof HandlerWrapper))
                return false;
            wrapper = (HandlerWrapper)wrapper.getHandler();
        }
    }
