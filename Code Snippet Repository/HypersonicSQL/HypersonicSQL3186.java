    void oneRound(String url, String user, String password,
                  boolean transactions,
                  boolean prepared) throws InterruptedException, SQLException {

        Vector      vClient  = new Vector();
        Thread      Client   = null;
        Enumeration e        = null;
        Connection  guardian = null;

        //
        this.transactions  = transactions;
        this.prepared_stmt = prepared;
        start_time         = System.currentTimeMillis();

        for (int i = 0; i < n_clients; i++) {
            Client = new ClientThread(n_txn_per_client, url, user, password,
                                      Connection.TRANSACTION_READ_COMMITTED);

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

        checkSums(guardian);
        connectClose(guardian);
    }
