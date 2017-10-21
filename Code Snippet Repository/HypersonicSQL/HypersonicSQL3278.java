    public void testKeepAlive() {

        Thread t1 = new TimeoutThread();

        t1.start();

        Thread t2 = new TimeoutThread();

        t2.start();

        Thread t3 = new TimeoutThread();

        t3.start();

        Thread t4 = new TimeoutThread();

        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {}

        System.out.println("testKeepAlive completed " + executeCount
                           + "connections.\n");
    }
