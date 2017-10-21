    public void printListCommandsCalled(Vector commands) {

        int commandCode = 0;

        for (int i = 0; i < commands.size(); i++) {
            System.out.println((String) commands.elementAt(i));
        }

        System.out.flush();
    }
