    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("EventMethods [pojoClass=");
        builder.append(pojoClass);
        builder.append(", onConnect=");
        builder.append(onConnect);
        builder.append(", onClose=");
        builder.append(onClose);
        builder.append(", onBinary=");
        builder.append(onBinary);
        builder.append(", onText=");
        builder.append(onText);
        builder.append(", onException=");
        builder.append(onError);
        builder.append(", onFrame=");
        builder.append(onFrame);
        builder.append("]");
        return builder.toString();
    }
