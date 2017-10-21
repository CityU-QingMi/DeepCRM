    @Override
    public byte[] getHeader() {
        final StringBuilder sbuf = new StringBuilder();
        append(sbuf, "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" ");
        appendLs(sbuf, "\"http://www.w3.org/TR/html4/loose.dtd\">");
        appendLs(sbuf, "<html>");
        appendLs(sbuf, "<head>");
        append(sbuf, "<meta charset=\"");
        append(sbuf, getCharset().toString());
        appendLs(sbuf, "\"/>");
        append(sbuf, "<title>").append(title);
        appendLs(sbuf, "</title>");
        appendLs(sbuf, "<style type=\"text/css\">");
        appendLs(sbuf, "<!--");
        append(sbuf, "body, table {font-family:").append(font).append("; font-size: ");
        appendLs(sbuf, headerSize).append(";}");
        appendLs(sbuf, "th {background: #336699; color: #FFFFFF; text-align: left;}");
        appendLs(sbuf, "-->");
        appendLs(sbuf, "</style>");
        appendLs(sbuf, "</head>");
        appendLs(sbuf, "<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">");
        appendLs(sbuf, "<hr size=\"1\" noshade=\"noshade\">");
        appendLs(sbuf, "Log session start time " + new java.util.Date() + "<br>");
        appendLs(sbuf, "<br>");
        appendLs(sbuf,
                "<table cellspacing=\"0\" cellpadding=\"4\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">");
        appendLs(sbuf, "<tr>");
        appendLs(sbuf, "<th>Time</th>");
        appendLs(sbuf, "<th>Thread</th>");
        appendLs(sbuf, "<th>Level</th>");
        appendLs(sbuf, "<th>Logger</th>");
        if (locationInfo) {
            appendLs(sbuf, "<th>File:Line</th>");
        }
        appendLs(sbuf, "<th>Message</th>");
        appendLs(sbuf, "</tr>");
        return sbuf.toString().getBytes(getCharset());
    }
