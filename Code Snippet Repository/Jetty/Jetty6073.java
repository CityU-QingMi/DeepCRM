    public AnnotatedEndpointScanner(AnnotatedEndpointMetadata<T, C> metadata)
    {
        this.metadata = metadata;

        paramsOnOpen = new LinkedList<>();
        paramsOnClose = new LinkedList<>();
        paramsOnError = new LinkedList<>();
        paramsOnMessage = new LinkedList<>();

        metadata.customizeParamsOnOpen(paramsOnOpen);
        paramsOnOpen.add(JsrParamIdOnOpen.INSTANCE);

        metadata.customizeParamsOnClose(paramsOnClose);
        paramsOnClose.add(JsrParamIdOnClose.INSTANCE);

        metadata.customizeParamsOnError(paramsOnError);
        paramsOnError.add(JsrParamIdOnError.INSTANCE);

        metadata.customizeParamsOnMessage(paramsOnMessage);
        paramsOnMessage.add(JsrParamIdText.INSTANCE);
        paramsOnMessage.add(JsrParamIdBinary.INSTANCE);
        paramsOnMessage.add(JsrParamIdPong.INSTANCE);
    }
