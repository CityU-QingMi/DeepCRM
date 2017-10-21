    private synchronized void update()
    {
        long now=System.nanoTime();
        long then=_nanoStamp.get();
        long duration=now-then;
                
        if (duration>SECOND_NANOS/2)
        {
            if (_nanoStamp.compareAndSet(then,now))
            {
                long msgsIn=_closedIn.sumThenReset();
                long msgsOut=_closedOut.sumThenReset();

                for (Map.Entry<Connection, Sample> entry : _samples.entrySet())
                {
                    Connection connection=entry.getKey();
                    Sample sample = entry.getValue();
                    Sample next = new Sample(connection);
                    if (_samples.replace(connection,sample,next))
                    {
                        msgsIn+=next._messagesIn-sample._messagesIn;
                        msgsOut+=next._messagesOut-sample._messagesOut;
                    }
                }
                
                _messagesInPerSecond=(int)(msgsIn*SECOND_NANOS/duration);
                _messagesOutPerSecond=(int)(msgsOut*SECOND_NANOS/duration);
            }
        }
    }
