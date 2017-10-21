    private void checkSystemInput() throws IOException 
    {     
        while (System.in.available() > 0) {
            int inputByte = System.in.read();
            if (inputByte >= 0) 
            {
                char c = (char)inputByte;
                if (c == '\n') {
                    restartWebApp();
                }
            }
        }
    }
