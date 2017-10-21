    public DefaultModelProblem( String message, Severity severity, Version version, String source, int lineNumber,
                                int columnNumber, String modelId, Exception exception )
    {
        this.message = message;
        this.severity = ( severity != null ) ? severity : Severity.ERROR;
        this.source = ( source != null ) ? source : "";
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
        this.modelId = ( modelId != null ) ? modelId : "";
        this.exception = exception;
        this.version = version;
    }
