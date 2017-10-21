    private void condlPrintln(String s, boolean printHtml) {
        if ((printHtml && !htmlMode) || (htmlMode && !printHtml)) return;

        if (shared.psStd != null) shared.psStd.println(s);

        if (pwQuery != null) {
            pwQuery.println(s);
            pwQuery.flush();
        }
    }
