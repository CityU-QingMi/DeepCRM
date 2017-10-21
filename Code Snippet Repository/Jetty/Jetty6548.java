    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append("JettyPojoMetadata[");
        s.append("onConnect=").append(onConnect);
        s.append(",onBinary=").append(onBinary);
        s.append(",onText=").append(onText);
        s.append(",onFrame=").append(onFrame);
        s.append(",onError=").append(onError);
        s.append(",onClose=").append(onClose);
        s.append("]");
        return s.toString();
    }
