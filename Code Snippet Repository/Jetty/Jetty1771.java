        @Override
        public void onOpen()
        {
            super.onOpen();
            if (_testFill)
                fillInterested();
            else
            {
                getExecutor().execute(new Runnable()
                {

                    @Override
                    public void run()
                    {
                        getEndPoint().write(_writeCallback,BufferUtil.toBuffer("Hello Client"));
                    }
                });
            }
        }
