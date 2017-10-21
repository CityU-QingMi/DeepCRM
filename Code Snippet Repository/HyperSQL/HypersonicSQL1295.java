    private void stdprintln(boolean queryOutput) {
        if (shared.psStd != null) {
            if (htmlMode) {
                shared.psStd.println("<BR>");
            } else {
                shared.psStd.println();
            }
        }

        if (queryOutput && pwQuery != null) {
            if (htmlMode) {
                pwQuery.println("<BR>");
            } else {
                pwQuery.println();
            }

            pwQuery.flush();
        }
    }
