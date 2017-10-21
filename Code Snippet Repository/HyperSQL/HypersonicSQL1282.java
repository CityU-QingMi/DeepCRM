    private void genRejectReportRecord(PrintWriter pw, int rCount,
            int lCount, String field, String eMsg, Throwable cause) {
        pw.println(SqltoolRB.rejectreport_row.getString(
                "sqltool-" + ((rCount % 2 == 0) ? "even" : "odd"),
                Integer.toString(rCount),
                Integer.toString(lCount),
                ((field == null) ? "" : field),
                (((eMsg == null) ? "" : eMsg)
                        + ((eMsg == null || cause == null) ? "" : "<HR/>")
                        + ((cause == null) ? "" : (
                                (cause instanceof SQLException
                                        && cause.getMessage() != null)
                                    ? cause.getMessage()
                                    : cause.toString()
                                )
                        )
                )));
    }
