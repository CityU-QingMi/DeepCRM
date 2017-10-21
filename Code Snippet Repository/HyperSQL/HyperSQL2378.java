    protected void printError(String msg) {

        PrintWriter writer = errWriter;

        if (writer != null) {
            writer.print("[" + serverId + "]: ");
            writer.print("[" + Thread.currentThread() + "]: ");
            writer.println(msg);
            writer.flush();
        }
    }
