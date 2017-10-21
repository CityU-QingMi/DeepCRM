    void process() {

        int len = vList.size();

        for (int i = 0; i < len; i++) {
            System.out.print(".");

            String file = (String) vList.elementAt(i);

            if (!processFile(file)) {
                System.out.println("in file " + file + " !");
            }
        }

        System.out.println("");
    }
