    private void doLoops(int thread, String name, int loops,boolean persistent) throws Exception
    {
        try
        {
            for (int i=0;i<loops;i++)
            {
                _loops[thread].set(i);
                doPaths(thread,name+"-"+i,persistent);
                Thread.sleep(1+_random.nextInt(20)*_random.nextInt(20));
                Thread.sleep(20);
            }
            _loops[thread].set(loops);
        }
        catch(Exception e)
        {
            System.err.println(e);
            _loops[thread].set(-_loops[thread].get());
            throw e;
        }
    }
