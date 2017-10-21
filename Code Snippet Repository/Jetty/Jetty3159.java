    public String newSessionId(long seedTerm)
    {
        // pick a new unique ID!
        String id=null;

        synchronized (_random)
        {
            while (id==null||id.length()==0)
            {
                long r0=_weakRandom
                        ?(hashCode()^Runtime.getRuntime().freeMemory()^_random.nextInt()^((seedTerm)<<32))
                        :_random.nextLong();
                        if (r0<0)
                            r0=-r0;

                        // random chance to reseed
                        if (_reseed>0 && (r0%_reseed)== 1L)
                        {
                            if (LOG.isDebugEnabled())
                                LOG.debug("Reseeding {}",this);
                            if (_random instanceof SecureRandom)
                            {
                                SecureRandom secure = (SecureRandom)_random;
                                secure.setSeed(secure.generateSeed(8));
                            }
                            else
                            {
                                _random.setSeed(_random.nextLong()^System.currentTimeMillis()^seedTerm^Runtime.getRuntime().freeMemory());
                            }
                        }

                        long r1=_weakRandom
                                ?(hashCode()^Runtime.getRuntime().freeMemory()^_random.nextInt()^((seedTerm)<<32))
                                :_random.nextLong();
                                if (r1<0)
                                    r1=-r1;

                                id=Long.toString(r0,36)+Long.toString(r1,36);

                                //add in the id of the node to ensure unique id across cluster
                                //NOTE this is different to the node suffix which denotes which node the request was received on
                                if (!StringUtil.isBlank(_workerName))
                                    id=_workerName + id;

                                id = id+Long.toString(COUNTER.getAndIncrement());

            }
        }
        return id;
    }
