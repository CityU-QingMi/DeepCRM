        public void execute(EventTrigger trigger, EventState<?> state, long timestamp)
        {
            if (trigger != null && state != null)
            {
                Collection<?> values = state.values();

                Iterator<?> it = values.iterator();
                while(it.hasNext())
                {
                    TriggerState<?> entry = (TriggerState<?>)it.next();
                    Object value = entry.getValue();
                    if (value != null)
                    {
                        _hits.add((Long)value);
                    }
                }
            }
        }
