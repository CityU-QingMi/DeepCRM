    public JsrEvents(AnnotatedEndpointMetadata<T, C> metadata)
    {
        this.metadata = metadata;
        this.onOpen = (metadata.onOpen == null)?null:new OnOpenCallable(metadata.onOpen);
        this.onClose = (metadata.onClose == null)?null:new OnCloseCallable(metadata.onClose);
        this.onError = (metadata.onError == null)?null:new OnErrorCallable(metadata.onError);
        this.onBinary = (metadata.onBinary == null)?null:new OnMessageBinaryCallable(metadata.onBinary);
        this.onBinaryStream = (metadata.onBinaryStream == null)?null:new OnMessageBinaryStreamCallable(metadata.onBinaryStream);
        this.onText = (metadata.onText == null)?null:new OnMessageTextCallable(metadata.onText);
        this.onTextStream = (metadata.onTextStream == null)?null:new OnMessageTextStreamCallable(metadata.onTextStream);
        this.onPong = (metadata.onPong == null)?null:new OnMessagePongCallable(metadata.onPong);
    }
