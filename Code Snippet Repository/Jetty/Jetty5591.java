    @Before
    public void before() throws Exception
    {
        _queue.clear();
        _pin=new PipedInputStream();
        _pout=new PipedOutputStream(_pin);
        _in=new ReadLineInputStream(_pin);
        _writer=new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    OutputStream out=_pout;
                    while (out!=null)
                    {
                        String s = _queue.poll(100,TimeUnit.MILLISECONDS);
                        if (s!=null)
                        {
                            if ("__CLOSE__".equals(s))
                                _pout.close();
                            else
                            {
                                _pout.write(s.getBytes(StandardCharsets.UTF_8));
                                Thread.sleep(50);
                            }
                        }
                        out=_pout;
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    _writer=null;
                }
              
            }
        };
        _writer.start();
    }
