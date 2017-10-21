    public void serve(SchedulerFactory schedFact, boolean console)
        throws Exception {
        sched = schedFact.getScheduler();

        sched.start();

        try {
            Thread.sleep(3000l);
        } catch (Exception ignore) {
        }

        System.out.println("\n*** The scheduler successfully started.");

        if (console) {
            System.out.println("\n");
            System.out
                    .println("The scheduler will now run until you type \"exit\"");
            System.out
                    .println("   If it was configured to export itself via RMI,");
            System.out.println("   then other process may now use it.");

            BufferedReader rdr = new BufferedReader(new InputStreamReader(
                    System.in));

            while (true) {
                System.out.print("Type 'exit' to shutdown the server: ");
                if ("exit".equals(rdr.readLine())) {
                    break;
                }
            }

            System.out.println("\n...Shutting down server...");

            sched.shutdown(true);
        }
    }
