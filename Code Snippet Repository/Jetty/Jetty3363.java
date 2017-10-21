    private void handle(Runnable run)
    {
        Action action = _state.handling();
        loop: while(true)
        {
            switch(action)
            {
                case DISPATCH:
                    if (run==null)
                        Assert.fail();
                    run.run();
                    break;
                    
                case READ_CALLBACK:
                    _in.run();
                    break;
                    
                case TERMINATED:
                case WAIT: 
                    break loop;
                    
                case COMPLETE: 
                    __history.add("COMPLETE");
                    break;
                    
                default:
                    Assert.fail();
            }
            action = _state.unhandle();
        }
    }
