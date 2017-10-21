    void skipBlanks() {
        final String cmd = this.command;
        final int    len = this.commandLength;

        top:
            while (currentIndex < len) {

            switch(cmd.charAt(currentIndex)) {
                case ' '  :
                case '\t' : {
                    currentIndex++;
                    continue top;
                }
            }

            break;
            }
    }
