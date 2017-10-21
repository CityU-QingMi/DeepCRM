    void oneRound(String url, String user,
                  String password) throws InterruptedException, SQLException {

        Vector      vClient  = new Vector();
        Thread      Client   = null;
        Enumeration e        = null;
        Connection  guardian = null;

        //
        start_time = System.currentTimeMillis();

        for (int i = 0; i < n_clients; i++) {
            if (useStoredProcedure) {
                Client = new ClientThreadProcedure(
                    n_txn_per_client, url, user, password,
                    Connection.TRANSACTION_READ_COMMITTED);
            } else {
                Client =
                    new ClientThread(n_txn_per_client, url, user, password,
                                     Connection.TRANSACTION_READ_COMMITTED);
            }

            Client.start();
            vClient.addElement(Client);
        }

/**/
/**/
/**/
        e = vClient.elements();

        while (e.hasMoreElements()) {
            Client = (Thread) e.nextElement();

            Client.join();
        }

        vClient.removeAllElements();
        reportDone();

        guardian = connect(url, user, password);

        if (count_results) {
            checkSums(guardian);
        }

        connectClose(guardian);
    }
