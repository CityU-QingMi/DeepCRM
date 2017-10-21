    @Override
    protected void doStop() throws Exception
    {
        super.doStop();
        close(ErrorCode.NO_ERROR.code, "stop", new Callback()
        {
            @Override
            public void succeeded()
            {
                disconnect();
            }

            @Override
            public void failed(Throwable x)
            {
                disconnect();
            }

            @Override
            public InvocationType getInvocationType()
            {
                return InvocationType.NON_BLOCKING;
            }
        });
    }
