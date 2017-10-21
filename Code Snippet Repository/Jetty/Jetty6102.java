    @Override
    public void init(JsrSession session)
    {
        super.init(session);
        idxPartialMessageFlag = findIndexForRole(Role.MESSAGE_PARTIAL_FLAG);

        EncoderFactory.Wrapper encoderWrapper = session.getEncoderFactory().getWrapperFor(returnType);
        if (encoderWrapper != null)
        {
            this.returnEncoder = encoderWrapper.getEncoder();
        }

        if (decodingType != null)
        {
            this.decoder = session.getDecoderFactory().getDecoderFor(decodingType);
        }
    }
