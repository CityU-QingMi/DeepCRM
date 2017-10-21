    public void trace(String s) {

        if ((s != null) &&!s.equals("")) {
            tMessage.setText(s);

            if (TRACE) {
                System.out.println(s);
            }
        }
    }
