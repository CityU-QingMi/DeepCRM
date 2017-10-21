    public void reportDone() {

        long end_time = System.currentTimeMillis();
        double completion_time = ((double) end_time - (double) start_time)
                                 / 1000;

        System.out.println("\n* Benchmark Report *");
        System.out.println("\n--------------------");
        System.out.println("Time to execute " + transaction_count
                           + " transactions: " + completion_time
                           + " seconds.");
        System.out.println("Max/Min memory usage: "
                           + (MemoryWatcher.max / 1024) + " / "
                           + (MemoryWatcher.min / 1024) + " kb");
        System.out.println(failed_transactions + " / " + transaction_count
                           + " failed to complete.");

        double rate = (transaction_count - failed_transactions)
                      / completion_time;

        System.out.println("Transaction rate: " + rate + " txn/sec.");
        System.out.print((MemoryWatcher.max / 1024) + ";"
                         + (MemoryWatcher.min / 1024) + ";"
                         + failed_transactions + ";" + rate + "\n");

        transaction_count   = 0;
        failed_transactions = 0;

        MemoryWatcher.reset();
    }
