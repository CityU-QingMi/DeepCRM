    public void testVerifyCollation() {

        String failedCollations = "";
        String failMessage      = "";

        while (collIterator.hasNext()) {
            String collationName = (String) collIterator.next();
            String message       = checkSorting(collationName);

            if (message.length() > 0) {
                if (failedCollations.length() > 0) {
                    failedCollations += ", ";
                }

                failedCollations += collationName;
                failMessage      += message;
            }
        }

        if (failedCollations.length() > 0) {
            fail("test failed for following collations:\n" + failedCollations
                 + "\n" + failMessage);
        }
    }
