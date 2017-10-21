    public DefaultProblem( String message, Severity severity, String source, int lineNumber, int columnNumber,
                                   Exception exception )
    {
        this.message = message;
        this.severity = ( severity != null ) ? severity : Severity.ERROR;
        this.source = ( source != null ) ? source : "";
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
        this.exception = exception;
    }
