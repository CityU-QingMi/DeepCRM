    private <T> void exec(EventTrigger trigger, EventState<T> state, long timestamp)
    {
        Collection<TriggerState<T>> trs = state.values();
        
        Properties data = new Properties();
        for (TriggerState<T> ts :  trs)
        {
            Object value = ts.getValue();

            StringBuffer buffer = new StringBuffer();
            buffer.append(value == null ? "" : value.toString());
            buffer.append("|");
            buffer.append(getClassID(value));
            buffer.append("||");
            buffer.append(ts.getDescription());
            
            data.setProperty(ts.getID(), buffer.toString());
            
            try
            {
                sendData(data);
            }
            catch (Exception ex)
            {
                LOG.debug(ex);
            }
        }
     }
