    public void startEcho()
    {
        if (echoThread != null)
        {
            throw new IllegalStateException("Echo thread already declared!");
        }
        echoThread = new Thread(this,"BlockheadServer/Echo");
        echoing.set(true);
        echoThread.start();
    }
