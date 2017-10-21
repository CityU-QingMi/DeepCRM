    String getStatusString()
    {
        synchronized (this)
        {
            return
            ((_state==__HANDLING)?"HANDLING":
                    (_state==__SUSPENDING)?"SUSPENDING":
                        (_state==__SUSPENDED)?"SUSPENDED":
                            (_state==__RESUMING)?"RESUMING":
                                (_state==__UNSUSPENDING)?"UNSUSPENDING":
                                    (_state==__COMPLETING)?"COMPLETING":
                                    ("???"+_state))+
            (_initial?",initial":"")+
            (_resumed?",resumed":"")+
            (_timeout?",timeout":"");
        }
    }
