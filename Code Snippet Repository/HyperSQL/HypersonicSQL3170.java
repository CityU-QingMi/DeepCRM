    public void run() {

        int msgType;

        init();

        if (session != null) {
            try {
                while (keepAlive) {
                    msgType = dataInput.readByte();

                    if (msgType < ResultConstants.MODE_UPPER_LIMIT) {
                        receiveResult(msgType);
                    } else {
                        receiveOdbcPacket((char) msgType);
                    }
                }
            } catch (CleanExit ce) {
                keepAlive = false;
            } catch (IOException e) {

                // fredt - is thrown when connection drops
                server.printWithThread(mThread + ":disconnected " + user);
            } catch (HsqlException e) {

                // fredt - is thrown in unforeseen circumstances
                if (keepAlive) {
                    server.printStackTrace(e);
                }
            } catch (Throwable e) {

                // fredt - is thrown in unforeseen circumstances
                if (keepAlive) {
                    server.printStackTrace(e);
                }
            }
        }

        close();
    }
