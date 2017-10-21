        public void execute(JobExecutionContext context)
                throws JobExecutionException {
            System.out.println("TestInterruptableJob is executing.");
            try {
                sync.await(); // wait for test thread to notice the job is now running
            } catch (InterruptedException e1) {
            } catch (BrokenBarrierException e1) {
            }
            for(int i=0; i < 200; i++) {
                try {
                    Thread.sleep(50); // simulate being busy for a while, then checking interrupted flag...
                } catch (InterruptedException ingore) { }
                if(TestInterruptableJob.interrupted.get()) {
                    System.out.println("TestInterruptableJob main loop detected interrupt signal.");
                    break;
                }
            }
            try {
                System.out.println("TestInterruptableJob exiting with interrupted = " + interrupted);
                sync.await();
            } catch (InterruptedException e) {
            } catch (BrokenBarrierException e) {
            }
        }
